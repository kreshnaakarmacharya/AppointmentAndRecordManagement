package com.hospital.hosp.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Specialization_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;

    @Column(name = "code")
    private long code;

    @Column( nullable = false, unique = true)
    private String name;

    @Column(name = "note")
    private String note;
}
