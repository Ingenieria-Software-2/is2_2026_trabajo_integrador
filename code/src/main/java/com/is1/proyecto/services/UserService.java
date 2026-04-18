package com.is1.proyecto.services;

import com.is1.proyecto.models.User;
import com.is1.proyecto.services.dto.UserCreateDTO;
import com.is1.proyecto.services.dto.UserLoginDTO;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    // -----------------------------------------------------------
    // Registra un nuevo usuario.
    // Devuelve el User creado.
    // -----------------------------------------------------------
    public User createUser(UserCreateDTO dto) {
        validateFields(dto.name, dto.password);
        checkNameAvailable(dto.name);

        User user = new User();
        user.setName(dto.name);
        user.setPassword(BCrypt.hashpw(dto.password, BCrypt.gensalt()));
        user.saveIt();

        return user;
    }

    // -----------------------------------------------------------
    // Autentica un usuario.
    // Devuelve el User si las credenciales son correctas.
    // Lanza ServiceException en cualquier caso de fallo
    // (mensaje genérico para no dar pistas de seguridad).
    // -----------------------------------------------------------
    public User login(UserLoginDTO dto) {
        validateFields(dto.username, dto.password);

        User user = User.findFirst("name = ?", dto.username);

        if (user == null || !BCrypt.checkpw(dto.password, user.getPassword())) {
            // Mismo mensaje para usuario inexistente y contraseña incorrecta:
            // no revelar cuál de los dos falló.
            throw new ServiceException("Usuario o contraseña incorrectos.", 401);
        }

        return user;
    }

    // -----------------------------------------------------------
    // Valida que nombre y contraseña no sean nulos ni vacíos.
    // -----------------------------------------------------------
    private void validateFields(String name, String password) {
        if (isBlank(name) || isBlank(password)) {
            throw new ServiceException(
                "Nombre y contraseña son requeridos.", 400);
        }
    }

    // -----------------------------------------------------------
    // Verifica que el nombre de usuario no esté tomado.
    // -----------------------------------------------------------
    private void checkNameAvailable(String name) {
        if (User.findFirst("name = ?", name) != null) {
            throw new ServiceException(
                "El nombre de usuario ya está en uso.", 409);
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}