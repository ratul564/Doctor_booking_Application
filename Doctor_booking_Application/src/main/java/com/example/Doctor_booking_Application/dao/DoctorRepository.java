package com.example.Doctor_booking_Application.dao;

import com.example.Doctor_booking_Application.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}
