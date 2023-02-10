package com.onwelo.mailsender;

import jakarta.persistence.*;

@Entity(name = "receiver")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
