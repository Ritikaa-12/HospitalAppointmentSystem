package com.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.User;
import com.it.services.AppointmentService;
import com.it.utils.ApiResponse;

@RestController
@RequestMapping("/auth/patient")
public class PatientController {

	@Autowired
	private AppointmentService appService;
	
	@GetMapping("/myRequests")
	public ApiResponse listRequests() {
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ApiResponse(true,"Requests List",appService.patientAppRequest(user));
	}
}
