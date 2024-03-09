package com.example.mini.service;

import com.example.mini.entity.Appointment;
import com.example.mini.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            return null;
        }
        appointment.setDoctorId(appointmentDetails.getDoctorId());
        appointment.setPatientId(appointmentDetails.getPatientId());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setPatientName(appointmentDetails.getPatientName());
        appointment.setDateOfAppointment(appointmentDetails.getDateOfAppointment());
        // Update other appointment attributes here
        return appointmentRepository.save(appointment);
    }

    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByAppointmentTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return appointmentRepository.findByAppointmentTimeBetween(startDateTime, endDateTime);
    }

    public List<Appointment> getAppointmentsByPatientNameContaining(String patientName) {
        return appointmentRepository.findByPatientNameContaining(patientName);
    }

    public List<Appointment> getAppointmentsByDateOfAppointment(LocalDate date) {
        return appointmentRepository.findByDateOfAppointment(date);
    }
}
