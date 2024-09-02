package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Clinic;
import com.example.demo.repository.ClinicRepository;

@Service
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Optional<Clinic> findById(Long id) {
        return clinicRepository.findById(id);
    }

    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public void deleteById(Long id) {
        clinicRepository.deleteById(id);
    }
}
