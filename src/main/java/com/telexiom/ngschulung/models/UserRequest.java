package com.telexiom.ngschulung.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@Entity
public class UserRequest implements Serializable {
    public String email;
    public String language = "en_GB";
    public String firstname;
    public String lastname;
    public Date birthdate;
    public String contactphone;

    public Boolean isValidRequest() {
        return !email.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && birthdate != null && !contactphone.isEmpty();
    }
}
