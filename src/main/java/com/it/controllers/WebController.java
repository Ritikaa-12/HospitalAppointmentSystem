package com.it.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.Doctor;
import com.it.entities.Patient;
import com.it.entities.User;
import com.it.models.AppointmentModel;
import com.it.models.LoginModel;
import com.it.models.LoginResponse;
import com.it.services.AppointmentService;
import com.it.services.DoctorService;
import com.it.services.UserService;
import com.it.utils.ApiResponse;
import com.it.utils.JWTUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospital")
public class WebController 
{
	@Autowired
	private UserService userService;
	@Autowired
	private DoctorService docService;
	@Autowired
	private AppointmentService appService;
	
	@GetMapping("/findpatient/{mobile}")
	public ApiResponse findPatient(@PathVariable(value="mobile") String mobile) {
		User user=userService.findByMobile(mobile);
		if(user==null || !user.getRole().equals("ROLE_PATIENT")) return new ApiResponse(false,"Patient Not Found !");
		else {
			Patient pat=(Patient) user;
			return new ApiResponse(true,"Patient Found!",pat);
		}
	}
	
	@PostMapping("/bookapt")
	public ApiResponse bookAppointment(@RequestBody AppointmentModel model) 
	{
		if(model.getPinfo()==null && model.getPid()==null)
		{
			return new ApiResponse(false, "Patient Info Not Supplied .");
		}else 
		{
			Doctor doc = docService.getById(model.getDoctorid());
			if(doc==null)
			{
				return new ApiResponse(false, "Doctor Not Found .");
			}
			else 
			{
				if(model.getPinfo()!=null) 
				{
					Patient pat = userService.savePatient(model.getPinfo());
					appService.saveRequest(pat,doc,model.getDate());
					return new ApiResponse(true, "Appointment Request Send Successfully.",null);
				}
				else 
				{
					Patient pat = (Patient) userService.getById(model.getPid());
					appService.saveRequest(pat,doc,model.getDate());
					return new ApiResponse(true, "Appointment Request Send Successfully.",null);
				}
			}	
		}
	}
	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody @Valid LoginModel model) 
	{
		User user = userService.loginCheck(model);
		if(user==null)
			return new ApiResponse(false, "Invalid User , Wrong Mobile or Password !");
		else 
		{
			// Token , User Role 
			String token = JWTUtils.createToken(user.getUserId());
			String role = user.getRole();
			return new ApiResponse(true, "Login Succes", new LoginResponse(token,role));
		}
	}
	
	@GetMapping("/listdocs")
	public ApiResponse listDoctor() 
	{
		List<Doctor> list = docService.list();
		return new ApiResponse(true, "Doctor Records !", list);
	}
	
	@RequestMapping("/authfailed")
	public ApiResponse authFailed() 
	{
		return new ApiResponse(false, "Unauthorized Request , Not Allowed !");
	}
}


 

