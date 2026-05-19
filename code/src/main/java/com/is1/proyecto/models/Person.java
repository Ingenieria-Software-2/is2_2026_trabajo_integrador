package com.is1.proyecto.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;



@Table("persons")
public class Person extends Model {

    public Integer getId() {
        return getInteger("id");
    }

    public String getDni() {
        return getString("dni");
    }

    public void setDni(String dni) {
        set("dni", dni);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getSurname() {
        return getString("surname");
    }

    public void setSurname(String surname) {
        set("surname", surname);
    }

    public String getCellphone() {
        return getString("cellphone");
    }

    public void setCellphone(String cellphone) {
        set("cellphone", cellphone);
    }

    public String getBirthdate() {
        return getString("birthdate");
    }

    public void setBirthdate(String birthdate) {
        set("birthdate", birthdate);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    // -------------------------
    // Authentication
    // -------------------------

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        set("username", username);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        set("password", password);
    }

    // -------------------------
    // Relationships
    // -------------------------

      public Professor getProfessor() {
        return Professor.findFirst("person_id = ?", getId());
    }

    public Student getStudent() {
        return Student.findFirst("person_id = ?", getId());
    }

    public Administrator getAdministrator() {
        return Administrator.findFirst("person_id = ?", getId());
    }
}