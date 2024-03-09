package com.example.mini.service;

import com.example.mini.entity.Prescription;
import com.example.mini.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);
        if (prescription == null) {
            return null;
        }
        prescription.setPatientId(prescriptionDetails.getPatientId());
        prescription.setDoctorId(prescriptionDetails.getDoctorId());
        prescription.setMedications(prescriptionDetails.getMedications());
        prescription.setInstructions(prescriptionDetails.getInstructions());
        // Update other prescription attributes here
        return prescriptionRepository.save(prescription);
    }

    public boolean deletePrescription(Long id) {
        if (prescriptionRepository.existsById(id)) {
            prescriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
