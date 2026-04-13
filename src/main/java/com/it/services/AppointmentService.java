package com.it.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.it.entities.Appointment;
import com.it.entities.AppointmentRequest;
import com.it.entities.Doctor;
import com.it.entities.Patient;
import com.it.entities.Receptionist;
import com.it.entities.User;
import com.it.models.AppointmentModel.PatientInfo;
import com.it.models.LoginModel;
import com.it.models.RecepModel;
import com.it.repositories.AppRequestRepo;
import com.it.repositories.AppointmentRepo;
import com.it.repositories.UserRepo;
import com.it.utils.UtilFuns;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Service
public class AppointmentService
{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MailService mailService;
	@Autowired
	private AppRequestRepo appReqRepo;
	@Autowired
	private AppointmentRepo appRepo;
	
	public void saveRequest(Patient pat, Doctor doc, LocalDate date) 
	{
	    // Block past dates (patient cannot request for previous day)
	    if (date.isBefore(LocalDate.now())) {
	        throw new RuntimeException("Cannot request an appointment for a past date");
	    }

	    // Block duplicate request (same patient + same doctor + same date)
	    Optional<AppointmentRequest> existingRequest =
	            appReqRepo.findByPatientAndDoctorAndAppointmentDateAndActiveStatus(
	                    pat, doc, date, true
	            );

	    if (existingRequest.isPresent()) {
	        throw new RuntimeException("You have already requested an appointment for this doctor on this date");
	    }

	    // Save request if everything is valid
	    AppointmentRequest req = new AppointmentRequest();
	    req.setPatient(pat);
	    req.setDoctor(doc);
	    req.setAppointmentDate(date);
	    req.setRequestDate(LocalDate.now());
	    req.setActiveStatus(true);
	    req.setStatus("Pending");

	    appReqRepo.save(req);
	}
	
	public List<AppointmentRequest> listAppRequest(){
		return appReqRepo.findRequests(LocalDate.now());
	}
	
	public List<AppointmentRequest> patientAppRequest(User user){
		return appReqRepo.findPatientRequests(LocalDate.now(),(Patient)user);
	}
	public AppointmentRequest getRequestById(Integer id) 
	{
		Optional<AppointmentRequest> op = appReqRepo.findById(id);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	public void save(Appointment app) {

	    //  Prevent booking on past dates
	    if (app.getAppointmentDate().isBefore(LocalDate.now())) {
	        throw new RuntimeException("Cannot book an appointment for a past date");
	    }

	    // Prevent duplicate slot booking (same doctor + same date + same time)
	    Optional<Appointment> existingBooking =
	            appRepo.findByDoctorAndAppointmentDateAndAppointmentTimeAndActiveStatus(
	                    app.getDoctor(),
	                    app.getAppointmentDate(),
	                    app.getAppointmentTime(),
	                    true
	            );

	    if (existingBooking.isPresent()) {
	        throw new RuntimeException("This time slot is already booked for this doctor");
	    }

	    //  Save if valid
	    appRepo.save(app);
	}
	
	public void updateRequest(AppointmentRequest req) {
		appReqRepo.save(req);
	}
	
	public List<Appointment> listAppointments()
	{
		return appRepo.findAppointments(LocalDate.now());
	}
	public List<Appointment> listPatientAppointments(User user)
	{
		return appRepo.findPatentAppointments(LocalDate.now(),(Patient)user);
	}
}

