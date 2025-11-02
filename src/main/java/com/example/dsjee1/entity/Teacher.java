package com.example.dsjee1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String teacherNumber;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    // 🔹 Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getTeacherNumber() { return teacherNumber; }
    public void setTeacherNumber(String teacherNumber) { this.teacherNumber = teacherNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
