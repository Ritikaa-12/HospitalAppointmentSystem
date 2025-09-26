package com.it.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.Receptionist;
import com.it.models.DoctorModel;
import com.it.models.RecepModel;
import com.it.services.DoctorService;
import com.it.services.UserService;
import com.it.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {

	@Autowired
	private DoctorService docService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test() {
		return "Testing Done";
	}
	@PostMapping("/savedoc")
	public ApiResponse saveDoctor(@Valid @RequestBody DoctorModel model) 
	{
		boolean status = docService.saveDoctor(model);
		if(status) {
			return new ApiResponse(true, "Doctor Saved !", null);
		}else {
			return new ApiResponse(false, "Doctor Not Saved !");
		}
	}
	@PostMapping("/saverecep")
	public ApiResponse save(@RequestBody @Valid RecepModel model) 
	{
		System.out.println(model);
		userService.saveRecep(model);
		return new ApiResponse(true, "Recep Saved !", null);
	}
	@GetMapping("/listrecep")
	public ApiResponse listRecep() 
	{
		List<Receptionist> lst = userService.listAllRecep();
		
		return new ApiResponse(true, "Recep Records !", lst);
	}
}
