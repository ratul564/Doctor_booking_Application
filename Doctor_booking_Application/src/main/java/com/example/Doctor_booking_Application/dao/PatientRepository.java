package com.example.Doctor_booking_Application.dao;

import com.example.Doctor_booking_Application.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

}
