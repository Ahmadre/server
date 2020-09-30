package com.telexiom.ngschulung.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@Entity
@Document(collection = "users")
public class User {

    @Id
    @Field("_id")
    public ObjectId id;

    public String getId() {
        return id.toString();
    }

    public String email;
    public String language;
    public String firstname;
    public String lastname;
    public Date birthdate;
    public String contactphone;

    @CreatedDate
    @Field("created_at")
    public Date createdAt;

    @LastModifiedDate
    @Field("updated_at")
    public Date updatedAt;

    public User(
            String email,
            String language,
            String firstname,
            String lastname,
            Date birthdate,
            String contactphone
    ) {
        this.email = email;
        this.language = language;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.contactphone = contactphone;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public User updateUser(
            ObjectId existingId,
            String email,
            String language,
            String firstname,
            String lastname,
            Date birthdate,
            String contactphone
    ) {
        this.id = existingId;
        this.email = email;
        this.language = language;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.contactphone = contactphone;
        this.updatedAt = new Date();

        return this;
    }
}