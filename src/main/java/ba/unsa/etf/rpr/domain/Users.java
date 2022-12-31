package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.UserDaoSQLimpl;

import java.util.Date;
import java.util.Objects;

public class Users extends UserDaoSQLimpl implements Idable {
private String username;
private int Id;
private int management;
private String first_name;
private String last_name;
private String gender;
private String password;
private String location;
private Date date_of_birth;

    public int getManagement() {
        return management;
    }

    public void setManagement(int management) {
        this.management = management;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Id == users.Id && Objects.equals(username, users.username) && Objects.equals(first_name, users.first_name) && Objects.equals(last_name, users.last_name) && Objects.equals(gender, users.gender) && Objects.equals(password, users.password) && Objects.equals(location, users.location) && Objects.equals(date_of_birth, users.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, Id, first_name, last_name, gender, password, location, date_of_birth);
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", user_id=" + Id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int anInt) {
Id=anInt;
    }
}
