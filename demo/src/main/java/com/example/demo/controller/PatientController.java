package com.example.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Patient;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/getAllPatients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.findById(id)
                .map(patient -> ResponseEntity.ok(patient))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createPatient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.findById(id)
                .map(existingPatient -> {
                    patient.setId(id);
                    Patient updatedPatient = patientService.save(patient);
                    return ResponseEntity.ok(updatedPatient);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("deletePatient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientService.findById(id).isPresent()) {
            patientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getPatientsByClinicId/{clinicId}")
    public ResponseEntity<List<Patient>> getPatientsByClinic(@PathVariable Long clinicId) {
    List<Patient> patients = patientService.findByClinicId(clinicId);
    return ResponseEntity.ok(patients);
}

    @GetMapping("/getPatientByAmka/{amka}")
    public ResponseEntity<Patient> getPatientByAmka(@PathVariable String amka) {
        return patientService.findByAmka(amka)
        .map(patient -> ResponseEntity.ok(patient))
        .orElse(ResponseEntity.notFound().build());
}

    @GetMapping("/searchByName")
    public ResponseEntity<List<Patient>> searchByName(
            @RequestParam String firstName, @RequestParam String lastName) {
        List<Patient> patients = patientService.searchByName(firstName, lastName);
        return ResponseEntity.ok(patients);
    }
}
