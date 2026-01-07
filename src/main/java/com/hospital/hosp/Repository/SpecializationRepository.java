package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
    List<Specialization> findAll();


}
