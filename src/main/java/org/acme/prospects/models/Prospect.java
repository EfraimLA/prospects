package org.acme.prospects.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "prospects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prospect {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final float LOW_RISK = 3.3F;
    private static final float MEDIUM_RISK = 6.6F;
    private static final float HIGH_RISK = 10F;
    private static final int DATE_CONSTANT = (1000 * 60 * 60 * 24 * 365);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @NotNull
    private Date birthdate;

    /*
     * m - male
     * f - female
     */
    @NotNull
    private Character gender;

    /**
     * m - mexican
     * f - foreign
     */
    @NotNull
    private Character nationality;

    /**
     * possible values (married, unmarried, single)
     */
    @NotNull
    private String maritalStatus;

    @NotNull
    private Integer economicDependents;

    /**
     * possible values (prepa, lic, pos)
     */
    @NotNull
    private String grade;

    @NotNull
    private Double income;

    /*
     * e - Estado de Cuenta
     * n - Nómina
     */
    @NotNull
    private Character incomeValidation;

    @NotNull
    private Integer jobAntiquity;

    @NotNull
    private Boolean creditHistory;

    /*
     * possible values (tdc, auto, otros)
     */
    @NotNull
    private String actualCredit;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private Date createdAt;

    @Transient
    private Double score;

    public String getScore() {
        var points = 0.0;

        if (this.getBirthdate() != null) {
            var age = (new Date().getTime() - this.getBirthdate().getTime()) / DATE_CONSTANT;

            if (age < 25) points += HIGH_RISK;
            else if (age <= 35) points += MEDIUM_RISK;
            else if (35 < age) points += LOW_RISK;
        }

        if (this.getNationality() != null) {
            switch (this.getNationality()) {
                case 'f':
                    points += HIGH_RISK;
                    break;
                case 'm':
                    points += LOW_RISK;
                    break;
            }
        }

        if (this.getMaritalStatus() != null) {
            switch (this.getMaritalStatus()) {
                case "single":
                    points += HIGH_RISK;
                    break;
                case "unmarried":
                    points += MEDIUM_RISK;
                    break;
                case "married":
                    points += LOW_RISK;
                    break;
            }
        }

        if (this.getEconomicDependents() != null) {
            if (this.getEconomicDependents() == 0) points += LOW_RISK;
            else if (this.getEconomicDependents() <= 2) points += MEDIUM_RISK;
            else if (this.getEconomicDependents() > 2) points += HIGH_RISK;
        }

        if (this.getGrade() != null) {
            switch (this.getGrade()) {
                case "prepa":
                    points += HIGH_RISK;
                    break;
                case "lic":
                    points += MEDIUM_RISK;
                    break;
                case "pos":
                    points += LOW_RISK;
                    break;
            }
        }

        if (this.getIncome() != null) {
            if (this.getIncome() < 5000) points += HIGH_RISK;
            else if (this.getIncome() <= 15000) points += MEDIUM_RISK;
            else if (this.getIncome() > 15000) points += LOW_RISK;
        }

        if (this.getIncomeValidation() != null) {
            switch (this.getIncomeValidation()) {
                case 'e':
                    points += HIGH_RISK;
                    break;
                case 'n':
                    points += LOW_RISK;
                    break;
            }
        }

        if (this.getJobAntiquity() != null) {
            if (this.getJobAntiquity() < 2) points += HIGH_RISK;
            else if (this.getJobAntiquity() <= 5) points += MEDIUM_RISK;
            else if (this.getJobAntiquity() > 5) points += LOW_RISK;
        }

        if (this.getCreditHistory() != null) {
            if (this.getCreditHistory()) points += LOW_RISK;
            else points += HIGH_RISK;
        }

        if (this.getActualCredit() != null) {
            switch (this.getActualCredit()) {
                case "tdc":
                    points += MEDIUM_RISK;
                    break;
                case "auto":
                    points += LOW_RISK;
                    break;
                case "otros":
                    points += HIGH_RISK;
                    break;
            }
        }

        return String.format("%.2f", points);
    }
}
