package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Patient;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findByClinicId(Long clinicId) {
        return patientRepository.findByClinicId(clinicId);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<Patient> findByAmka(String amka) {
        return patientRepository.findByAmka(amka);
    }

    public List<Patient> searchByName(String firstName, String lastName) {
        return patientRepository.findByLastNameAndFirstName(lastName, firstName);
    }
}
