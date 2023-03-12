package com.example.Doctor_booking_Application.service;



import com.example.Doctor_booking_Application.dao.DoctorRepository;
import com.example.Doctor_booking_Application.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository repository;

    public Doctor saveDoctor(Doctor doctor){

        String doctorName = doctor.getDoctorName();
        doctorName = "Dr. " + doctorName;
        doctor.setDoctorName(doctorName);

        return repository.save(doctor);

    }

    public List<Doctor> getDoctor(String doctorId) {

        List<Doctor> doctorList;

        if(null!=doctorId){
            doctorList = new ArrayList<>();
            doctorList.add(repository.findById(Integer.valueOf(doctorId)).get());
        }
        else{
            doctorList = repository.findAll();
        }

        return doctorList;

    }

    public Doctor getDoctorById(String doctorId) {
        return repository.findById(Integer.valueOf(doctorId)).get();
    }

    public void updateDoctor(int doctorId, Doctor newDoctor) {
        Doctor doctor = repository.findById(doctorId).get();

        doctor.setDoctorId(newDoctor.getDoctorId());
        doctor.setDoctorName(newDoctor.getDoctorName());
        doctor.setExperience(newDoctor.getExperience());
        doctor.setSpecializedIn(newDoctor.getSpecializedIn());

        repository.save(doctor);
    }

    public void deleteDoctor(int doctorId) {
        repository.deleteById(doctorId);
    }
}

