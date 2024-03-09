package com.example.mini.repository;

import com.example.mini.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long>{
    List<Patient> findByNameContaining(String name);
    List<Patient> findByAgeGreaterThan(int age);
    List<Patient> findByGender(String gender);
}
