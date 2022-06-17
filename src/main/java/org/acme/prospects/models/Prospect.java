package org.acme.prospects.models;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prospects")
@Data
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private Date birthdate;

    private Character gender;

    private Character nationality;

    private String maritalStatus;

    private String grade;

    // Financial Data
    private Integer income;

    // E stands for Estado de Cuenta & N for Nómina
    private Character incomeValidation;

    private Integer jobAntiquity;

    private Boolean creditHistory;

    private String actualCredit;

    @CreationTimestamp
    private Date createdAt;
}
