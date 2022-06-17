package org.acme.prospects.models;

import javax.persistence.*;

@Entity
@Table(name = "prospects")
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String phone;


}
