package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Patient;

import jakarta.persistence.Table;

@Repository
@Table(name = "patients")
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByClinicId(Long clinicId);

    Optional<Patient> findByAmka(String amka);

    List<Patient> findByLastNameAndFirstName(String lastName, String firstName);
}
