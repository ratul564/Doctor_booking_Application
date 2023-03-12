package com.example.Doctor_booking_Application.controller;


import com.example.Doctor_booking_Application.dao.DoctorRepository;
import com.example.Doctor_booking_Application.model.Doctor;
import com.example.Doctor_booking_Application.model.Patient;
import com.example.Doctor_booking_Application.service.PatientService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PatientController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientService service;

    @PostMapping(value = "/patient")
    public String savePatient(@RequestBody String patientRequest){

        JSONObject json = new JSONObject(patientRequest);
        Patient patient = setPatient(json);
        service.savePatient(patient);
        return "Saved Patient";
    }

    private Patient setPatient(JSONObject json) {

        Patient patient = new Patient();

        patient.setPatientId(json.getInt("patientId"));
        patient.setPatientName(json.getString("patientName"));
        patient.setAge(json.getInt("age"));
        patient.setPhoneNumber(json.getString("phoneNumber"));
        patient.setDiseaseType(json.getString("diseaseType"));
        patient.setGender(json.getString("gender"));

        Timestamp currTime = new Timestamp(System.currentTimeMillis());
        patient.setAdmitDate(currTime);

        String doctorId = json.getString("doctorId");
        Doctor doctor = doctorRepository.findById(Integer.valueOf(doctorId)).get();

        patient.setDoctor(doctor);

        return patient;

    }
    @GetMapping(value = "/patient")
    public ResponseEntity getPatients(@Nullable @RequestParam String doctorId,
                                      @Nullable @RequestParam String patientId){
        JSONArray patientDetails = service.getPatients(doctorId,patientId);
        return new ResponseEntity<>(patientDetails.toString(),HttpStatus.OK);
    }

    @PutMapping(value = "/update-patient/{patientId}")
    public void updateDoctor(@PathVariable int patientId, @RequestBody Patient patient){
        service.updatePatient(patientId,patient);
    }

    @DeleteMapping(value = "/delete-patient/{patientId}")
    public void deletePatient(@PathVariable int patientId){
        service.deletePatient(patientId);
    }
}

