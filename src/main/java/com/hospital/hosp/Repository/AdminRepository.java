package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUsernameAndPassword(String username,String password);
}
