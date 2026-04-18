package com.is1.proyecto.services;

import com.is1.proyecto.models.Person;
import com.is1.proyecto.models.Professor;
import com.is1.proyecto.services.dto.ProfessorCreateDTO;

public class ProfessorService {

    private static final String EMAIL_REGEX =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
        "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    // -----------------------------------------------------------
    // Método público principal: recibe un DTO, devuelve el Professor creado.
    // El Controller solo llama a esto. No sabe nada de lo que pasa adentro.
    // -----------------------------------------------------------
    public Professor createProfessor(ProfessorCreateDTO dto) {

        // PASO 1: Validar campos obligatorios y formatos
        validateFields(dto);

        // PASO 2: Verificar que DNI y mail no estén ya registrados
        Integer dni = parseDni(dto.dniStr);
        checkUniqueness(dto.mail, dni);

        // PASO 3: Persistir en orden (primero Person, después Professor)
        return persist(dto, dni);
    }

    // -----------------------------------------------------------
    // PASO 1: Validaciones de formato y presencia
    // Lanza ServiceException(400) si algo está mal.
    // -----------------------------------------------------------
    private void validateFields(ProfessorCreateDTO dto) {
        if (isBlank(dto.nombre) || isBlank(dto.apellido)
                || isBlank(dto.mail) || isBlank(dto.dniStr)) {
            throw new ServiceException(
                "Todos los campos obligatorios son requeridos.", 400);
        }

        if (!dto.mail.matches(EMAIL_REGEX)) {
            throw new ServiceException(
                "El formato del email no es válido.", 400);
        }
    }

    // -----------------------------------------------------------
    // Parsea el DNI a Integer. Separado porque también lo usa checkUniqueness.
    // Lanza ServiceException(400) si no es un número.
    // -----------------------------------------------------------
    private Integer parseDni(String dniStr) {
        try {
            return Integer.parseInt(dniStr);
        } catch (NumberFormatException e) {
            throw new ServiceException("El DNI debe ser un número válido.", 400);
        }
    }

    // -----------------------------------------------------------
    // PASO 2: Consulta al modelo si ya existen esos datos únicos.
    // Lanza ServiceException(409 Conflict) si están duplicados.
    // -----------------------------------------------------------
    private void checkUniqueness(String mail, Integer dni) {
        if (Person.findFirst("mail = ?", mail) != null) {
            throw new ServiceException(
                "El email ya está registrado en el sistema.", 409);
        }
        if (Person.findFirst("dni = ?", dni) != null) {
            throw new ServiceException(
                "El DNI ya está registrado en el sistema.", 409);
        }
    }

    // -----------------------------------------------------------
    // PASO 3: Persiste Person y luego Professor.
    // El orden importa: Professor necesita el ID de Person.
    // -----------------------------------------------------------
    private Professor persist(ProfessorCreateDTO dto, Integer dni) {
        Person person = new Person();
        person.setNombre(dto.nombre);
        person.setApellido(dto.apellido);
        person.setMail(dto.mail);
        person.setDni(dni);
        person.saveIt(); // genera el ID que usará Professor

        Professor professor = new Professor();
        professor.set("person_id", person.getId());

        if (!isBlank(dto.legajoStr)) {
            try {
                professor.setLegajo(Integer.parseInt(dto.legajoStr));
            } catch (NumberFormatException e) {
                throw new ServiceException(
                    "El legajo debe ser un número válido.", 400);
            }
        }
        if (!isBlank(dto.titulo))      professor.setTitulo(dto.titulo);
        if (!isBlank(dto.universidad)) professor.setUniversidadGraduado(dto.universidad);
        if (!isBlank(dto.cargo))       professor.setCargo(dto.cargo);

        professor.saveIt();
        return professor;
    }

    // -----------------------------------------------------------
    // Helper: null o vacío
    // -----------------------------------------------------------
    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}