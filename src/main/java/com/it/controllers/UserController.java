package com.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.User;
import com.it.models.PWDModel;
import com.it.services.UserService;
import com.it.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PatchMapping("/changepwd")
	public ApiResponse changePwd(@RequestBody @Valid PWDModel model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getPassword().equals(model.getOldpwd())) 
		{
			user.setPassword(model.getNewpwd());
			userService.update(user);
			return new ApiResponse(true, "Password Changed",null);
		}else {
			return new ApiResponse(false, "Old Password Not Match !");
		}
	}
	
}