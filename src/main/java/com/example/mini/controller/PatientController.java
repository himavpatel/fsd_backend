package com.example.mini.controller;

import com.example.mini.entity.Patient;
import com.example.mini.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return updatedPatient != null ?
                new ResponseEntity<>(updatedPatient, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        boolean deleted = patientService.deletePatient(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatientsByName(@RequestParam("name") String name) {
        List<Patient> patients = patientService.searchPatientsByName(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/ageGreaterThan/{age}")
    public ResponseEntity<List<Patient>> getPatientsByAgeGreaterThan(@PathVariable("age") int age) {
        List<Patient> patients = patientService.getPatientsByAgeGreaterThan(age);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Patient>> getPatientsByGender(@PathVariable("gender") String gender) {
        List<Patient> patients = patientService.getPatientsByGender(gender);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
