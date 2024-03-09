package com.example.mini.repository;

import com.example.mini.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByAppointmentTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Appointment> findByPatientNameContaining(String patientName);
    List<Appointment> findByDateOfAppointment(LocalDate date);
}
