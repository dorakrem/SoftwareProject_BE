package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Clinic;

import jakarta.persistence.Table;

@Repository
@Table(name = "clinics")
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
