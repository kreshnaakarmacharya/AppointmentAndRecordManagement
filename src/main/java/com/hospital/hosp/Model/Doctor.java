package com.hospital.hosp.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Doctor_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="Phone")
    private String phone;

    @Column(name="Gender")
    private String gender;

    @Column(name="Note")
    private String note;

    @Column(name="specialization_id")
    private long spec;
}
