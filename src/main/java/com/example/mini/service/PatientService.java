package com.example.mini.service;

import com.example.mini.entity.Patient;
import com.example.mini.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
//    public Patient getPatientById(Long id) {
//        return patientRepository.findById(id).orElse(null);
//    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            return null;
        }
        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setEmailId(patientDetails.getEmailId());
        // Update other patient attributes here
        return patientRepository.save(patient);
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContaining(name);
    }
    public List<Patient> getPatientsByAgeGreaterThan(int age) {
        return patientRepository.findByAgeGreaterThan(age);
    }

    public List<Patient> getPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }
}
