package com.example.Doctor_booking_Application.controller;


import com.example.Doctor_booking_Application.model.Doctor;
import com.example.Doctor_booking_Application.service.DoctorService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class DoctorController {

    @Autowired
    DoctorService service;

    @PostMapping(value = "/doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody String requestDoctor){

        JSONObject json = new JSONObject(requestDoctor);

        List<String> validationList = validateDoctor(json);

        if(validationList.isEmpty()){
            Doctor doctor = setDoctor(json);
            service.saveDoctor(doctor);
            return new ResponseEntity<>("Saved Doctor", HttpStatus.CREATED);
        }
        else{
            String[] answer = Arrays.copyOf(validationList.toArray(),validationList.size(),String[].class);
            return new ResponseEntity<>("Please pass mandatory parameters- "+
                    Arrays.toString(answer),HttpStatus.BAD_REQUEST);
        }

    }

    private List<String> validateDoctor(JSONObject json) {

        Map<Doctor,List<String>> errorMap = new HashMap<>();

        List<String> errorList = new ArrayList<>();

        if(!
                json.has("doctorId")){
            errorList.add("doctorId");
        }

        if(!json.has("doctorName")){
            errorList.add("doctorName");
        }

        if(!json.has("specializedIn")){
            errorList.add("specializedIn");
        }

        return  errorList;
    }

    public Doctor setDoctor(JSONObject json){
        Doctor doctor = new Doctor();

        String doctorId = json.getString("doctorId");
        doctor.setDoctorId(Integer.valueOf(doctorId));

        String doctorName = json.getString("doctorName");
        doctor.setDoctorName(doctorName);

        String specializedIn = json.getString("specializedIn");
        doctor.setSpecializedIn(specializedIn);

        if(json.has("experience")){
            String experience = json.getString("experience");
            doctor.setExperience(experience);
        }
        return doctor;
    }


    @GetMapping(value = "/doctor")
    public List<Doctor> getDoctor(@Nullable @RequestParam String doctorId){
        return service.getDoctor(doctorId);
    }

    @PutMapping(value = "/update-doctor/{doctorId}")
    public void updateDoctor(@PathVariable int doctorId, @RequestBody Doctor doctor){
        service.updateDoctor(doctorId,doctor);
    }

    @DeleteMapping(value = "/delete-doctor/{doctorId}")
    public void deleteDoctor(@PathVariable int doctorId){
        service.deleteDoctor(doctorId);
    }
}
