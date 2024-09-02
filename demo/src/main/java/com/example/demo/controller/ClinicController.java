package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Clinic;
import com.example.demo.service.ClinicService;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping("/getAllClinics")
    public ResponseEntity<List<Clinic>> getAllClinics() {
        List<Clinic> clinics = clinicService.findAll();
        return ResponseEntity.ok(clinics);
    }

    @GetMapping("/getClinicById/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable Long id) {
        Optional<Clinic> clinic = clinicService.findById(id);
        return clinic.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createClinic")
    public ResponseEntity<Clinic> createClinic(@RequestBody Clinic clinic) {
        Clinic createdClinic = clinicService.save(clinic);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClinic);
    }

    @PutMapping("/updateClinic/{id}")
    public ResponseEntity<Clinic> updateClinic(@PathVariable Long id, @RequestBody Clinic clinic) {
        return clinicService.findById(id)
                .map(existingClinic -> {
                    clinic.setId(id);
                    Clinic updatedClinic = clinicService.save(clinic);
                    return ResponseEntity.ok(updatedClinic);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteClinic/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        if (clinicService.findById(id).isPresent()) {
            clinicService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
