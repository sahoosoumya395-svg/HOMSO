package com.homso.care.repository;

import com.homso.care.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByMrnNo(String mrnNo);

    Optional<Patient> findByUserId(Long userId);

    boolean existsByMrnNo(String mrnNo);
}