package com.example.Doctor_booking_Application.service;


import com.example.Doctor_booking_Application.dao.PatientRepository;
import com.example.Doctor_booking_Application.model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;

    public void savePatient(Patient patient){

        PatientService obj = new PatientService();

        System.out.println("Name of the class id - "+ obj.getClass().getName());

        System.out.println("Name of the class of interface - "+ repository.getClass().getName());

        repository.save(patient);
    }


    public JSONArray getPatients(String doctorId, String patientId) {

        List<Patient> patientList = repository.findAll();

        JSONArray patientArr = new JSONArray();

        for (Patient patient: patientList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("patientId",patient.getPatientId());
            jsonObject.put("patientName",patient.getPatientName());
            jsonObject.put("age",patient.getAge());
            jsonObject.put("phoneNumber",patient.getPhoneNumber());
            jsonObject.put("diseaseType",patient.getDiseaseType());
            jsonObject.put("gender",patient.getGender());
            jsonObject.put("admitDate",patient.getAdmitDate());
            jsonObject.put("doctorId",patient.getDoctor().getDoctorId());
            patientArr.put(jsonObject);
        }
        return patientArr;
    }

    public void updatePatient(int patientId, Patient newPatient) {

        Patient patient = repository.findById(patientId).get();

        patient.setPatientId(newPatient.getPatientId());
        patient.setPatientName(newPatient.getPatientName());
        patient.setAge(newPatient.getAge());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setDiseaseType(newPatient.getDiseaseType());
        patient.setGender(newPatient.getGender());
        patient.setDoctor(newPatient.getDoctor());

        repository.save(patient);
    }

    public void deletePatient(int patientId) {
        repository.deleteById(patientId);
    }
}

