package com.iessanalberto.dam1.jdbc.services;

import com.iessanalberto.dam1.jdbc.models.Usuario;
import com.iessanalberto.dam1.jdbc.repositories.LoginRepository;

public class LoginServices {
    public Usuario login (String user, String password) throws Exception {
       LoginRepository loginRepository = new LoginRepository();
        if (user.isEmpty() || password.isEmpty()) {
            throw new Exception("Inserte usuario y contraseña");


        }
        return loginRepository.login(user, password);
    }
    public void register (String name, String user, String password, String passwordRepeat) throws Exception {
        LoginRepository loginRepository = new LoginRepository();
        if (name.isEmpty() || user.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
            throw new Exception("Rellene todos los campos");
        } else if (!password.equals(passwordRepeat)) {
            throw new Exception("Las contraseñas no coinciden");
        } else if (name.length()>50 || user.length()>50 || password.length()>50 ){
            throw new Exception("Los campos no pueden superar los 50 caracteres");
        }

        loginRepository.register(name,user,password);
        }

    }


