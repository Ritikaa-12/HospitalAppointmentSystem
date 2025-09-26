package com.it.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.Appointment;
import com.it.entities.AppointmentRequest;
import com.it.entities.User;
import com.it.models.PWDModel;
import com.it.services.AppointmentService;
import com.it.services.UserService;
import com.it.utils.ApiResponse;


@RestController
@RequestMapping("/auth/recep")
public class RecepController 
{
	@Autowired
	private AppointmentService appService;	
	
	@GetMapping("/listRequests")
	public ApiResponse listRequests() 
	{
		return new ApiResponse(true, "Requests List",appService.listAppRequest());
	}
	
	@PostMapping("/approveRequest/{reqid}")
	public ApiResponse approveRequest(@PathVariable(value = "reqid") Integer reqid) 
	{
		AppointmentRequest req = appService.getRequestById(reqid);
		if(req==null || req.getActiveStatus()==false)
			return new ApiResponse(false, "Request Not Found or Request already cancelled.");
		else {
			Appointment app = new Appointment();
			app.setPatient(req.getPatient());
			app.setAppointmentDate(req.getAppointmentDate());
			app.setAppointmentTime(LocalTime.of(14, 25, 0));
			app.setDoctor(req.getDoctor());
			app.setType("online");
			app.setRequest(req);
			app.setActiveStatus(true);
			app.setStatus("approve");
			appService.save(app);
			
			req.setStatus("approve");
			req.setActiveStatus(false);
			appService.updateRequest(req);
			return new ApiResponse(true, "Appointment Done",null);
		}		
	}	
	@PatchMapping("/cancelRequest/{reqid}")
	public ApiResponse cancelRequest(@PathVariable(value = "reqid") Integer reqid) 
	{
		AppointmentRequest req = appService.getRequestById(reqid);
		if(req==null)
			return new ApiResponse(false, "Request Not Found");
		else {
			req.setActiveStatus(false);
			req.setStatus("cancel");
			appService.updateRequest(req);
			return new ApiResponse(true, "Request Cancelled",null);
		}		
	}
	
	@GetMapping("/listAppointments")
	public ApiResponse listAppointments() 
	{
		return new ApiResponse(true, "Appointments List",appService.listAppointments());
	}
}
