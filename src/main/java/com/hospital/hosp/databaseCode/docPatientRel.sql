CREATE TABLE doctor_patient_relation(
    id BIGINT AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(doctor_id) REFERENCES doctor_tbl(id),
    FOREIGN KEY(patient_id) REFERENCES patient_tbl(id)
);