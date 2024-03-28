package by.bsuir.wtl2.webapp.classes.entities;

import java.sql.Date;

/**
 * The Order class represents an order.
 * It includes properties such as ID, creation date, summary price, and acceptance status.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class User {

    private int id;
    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String login;

    private String password;

    private Date registrationDate;

    public User(){

    }

    public User(int id,String name,String surname,String phoneNumber,String email,
                String login,String password,Date registrationDate){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
