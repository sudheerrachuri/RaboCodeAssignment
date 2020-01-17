package com.cts.codeassignment.formsubmission.beans;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    @Nonnull
    private int id;
    @Column(name = "us_name")
    @Nonnull
    private String name;
    @Column(name = "us_mobilenumber")
    @Nonnull
    private String mobileNumber;
    @Column(name = "us_language")
    @Nonnull
    private String language;
    @Column(name = "us_email")
    @Nonnull
    private String email;
    @Column(name = "us_gender")
    @Nonnull
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", language='" + language + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getMobileNumber(), user.getMobileNumber()) &&
                Objects.equals(getLanguage(), user.getLanguage()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getGender(), user.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMobileNumber(), getLanguage(), getEmail(), getGender());
    }
}
