package com.it.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.entities.Appointment;
import com.it.entities.Doctor;
import com.it.entities.Patient;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	@Query("SELECT R from Appointment as R where appointmentDate>=?1 and activeStatus=true ORDER BY appointmentDate")
	List<Appointment> findAppointments(LocalDate appointmentDate);
	
	@Query("SELECT R from Appointment as R where appointmentDate>=?1 and patient=?2 ORDER BY appointmentDate")
	List<Appointment> findPatentAppointments(LocalDate appointmentDate,Patient patient);
	
	Optional<Appointment> findByDoctorAndAppointmentDateAndAppointmentTimeAndActiveStatus(
	            Doctor doctor,
	            LocalDate appointmentDate,
	            LocalTime appointmentTime,
	            Boolean activeStatus
	    );
}
