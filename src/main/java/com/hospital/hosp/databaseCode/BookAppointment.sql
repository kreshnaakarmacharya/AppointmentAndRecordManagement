CREATE TABLE Book_Appointment (
                                  id BIGINT AUTO_INCREMENT,
                                  title VARCHAR(255) NOT NULL,
                                  description VARCHAR(255) NOT NULL,
                                  specialization_id BIGINT NOT NULL,
                                  date DATE NOT NULL,
                                  time TIME NOT NULL,
                                  doc_id BIGINT NOT NULL,
                                  patient_id BIGINT NOT NULL ,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (doc_id) REFERENCES doctor_tbl(id),
                                  FOREIGN KEY (patient_id) references patient_tbl(id),
                                  FOREIGN KEY (specialization_id) references specialization_table(id)
);

ALTER TABLE book_appointment
    ADD status VARCHAR(50);