package com.example.mini.controller;

import com.example.mini.entity.Appointment;
import com.example.mini.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return updatedAppointment != null ?
                new ResponseEntity<>(updatedAppointment, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable("doctorId") Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable("patientId") Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/appointmentTime")
    public ResponseEntity<List<Appointment>> getAppointmentsByAppointmentTimeBetween(@RequestParam("startDateTime") LocalDateTime startDateTime,
                                                                                     @RequestParam("endDateTime") LocalDateTime endDateTime) {
        List<Appointment> appointments = appointmentService.getAppointmentsByAppointmentTimeBetween(startDateTime, endDateTime);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/patientName")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientNameContaining(@RequestParam("patientName") String patientName) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientNameContaining(patientName);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDateOfAppointment(@RequestParam("date") LocalDate date) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDateOfAppointment(date);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

}
