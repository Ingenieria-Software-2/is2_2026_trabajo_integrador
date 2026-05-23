package com.is1.proyecto.services;

import com.is1.proyecto.models.Administrator;
import com.is1.proyecto.models.Person;
import com.is1.proyecto.models.PersonRole;
import com.is1.proyecto.models.Role;

import com.is1.proyecto.services.dto.PersonCreateDTO;
import com.is1.proyecto.services.dto.PersonLoginDTO;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    // -----------------------------------------------------------
    // Registra una nueva persona.
    // Devuelve la Person creada.
    // -----------------------------------------------------------
    public Person createPerson(PersonCreateDTO dto) {
        validateFields(dto.name, dto.password);
        checkUsernameAvailable(dto.name);

        Person person = new Person();  
        person.setDni(dto.dni);
        person.setName(dto.name);
        person.setSurname(dto.surname);
        person.setUsername(dto.username);
        person.setEmail(dto.email);
        person.setCellphone(dto.cellphone);
        person.setBirthdate(dto.birthdate);

        person.setPassword(
            BCrypt.hashpw(dto.password, BCrypt.gensalt())
        );
        person.saveIt();

        return person;
    }
    //Temporalmente tendremos createAdministrator en el AuthService
    //Más adelante deberá ser un AdminService para respetar separacion de
    // responsabilidades de la capa de servicios
    public Person createAdministrator(PersonCreateDTO dto) {

        validateFields(dto.username, dto.password);

        checkUsernameAvailable(dto.username);

        Person person = new Person();

        person.setDni(dto.dni);
        person.setName(dto.name);
        person.setSurname(dto.surname);
        person.setUsername(dto.username);
        person.setEmail(dto.email);
        person.setCellphone(dto.cellphone);
        person.setBirthdate(dto.birthdate);

        person.setPassword(
            BCrypt.hashpw(dto.password, BCrypt.gensalt())
        );

        person.saveIt();

        // -------------------------
        // ROLE
        // -------------------------

        PersonRole personRole = new PersonRole();

        personRole.setPersonId(person.getId());
        personRole.setRole(Role.ADMIN);

        personRole.saveIt();

        // -------------------------
        // ADMINISTRATOR
        // -------------------------

        Administrator administrator = new Administrator();

        administrator.set("person_id", person.getId());

        administrator.saveIt();

        return person;
    }
    // -----------------------------------------------------------
    // Autentica un usuario.
    // Devuelve la Person si las credenciales son correctas.
    // Lanza ServiceException en cualquier caso de fallo
    // (mensaje genérico para no dar pistas de seguridad).
    // -----------------------------------------------------------
    public Person login(PersonLoginDTO dto) {
        validateFields(dto.username, dto.password);

        Person person = Person.findFirst("username = ?", dto.username);

        if (person == null || !BCrypt.checkpw(dto.password, person.getPassword())) {
            // Mismo mensaje para usuario inexistente y contraseña incorrecta:
            // no revelar cuál de los dos falló.
            throw new ServiceException("Usuario o contraseña incorrectos.", 401);
        }

        return person;
    }

    // -----------------------------------------------------------
    // Valida que username y contraseña no sean nulos ni vacíos.
    // -----------------------------------------------------------
    private void validateFields(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            throw new ServiceException(
                "Nombre y contraseña son requeridos.", 400);
        }
    }

    // -----------------------------------------------------------
    // Verifica que el username de la Persona no esté tomado.
    // -----------------------------------------------------------
    private void checkUsernameAvailable(String username) {
        if (Person.findFirst("username = ?", username) != null) {
            throw new ServiceException(
                "El nombre de usuario ya está en uso.", 409);
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}