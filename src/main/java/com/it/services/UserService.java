package com.it.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.it.entities.Doctor;
import com.it.entities.Patient;
import com.it.entities.Receptionist;
import com.it.entities.User;
import com.it.models.AppointmentModel.PatientInfo;
import com.it.models.LoginModel;
import com.it.models.RecepModel;
import com.it.repositories.DoctorRepo;
import com.it.repositories.UserRepo;
import com.it.utils.UtilFuns;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MailService mailService;
	@Autowired
	private DoctorRepo docRepo;
	
	public void update(User user) 
	{
		userRepo.save(user);
	}

	public void saveRecep(RecepModel model) 
	{		
		try {
			String pwd = Integer.toString(UtilFuns.generatePWD());
			Receptionist obj = new Receptionist(model,pwd);
			userRepo.save(obj);
			
			mailService.userSaveMailSender(model.getName(), model.getEmail(), pwd);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Receptionist> listAllRecep() 
	{
		return userRepo.findByRole("ROLE_RECEP");
	}

	public User loginCheck(@Valid LoginModel model) 
	{
		Optional<User> op = userRepo.findByMobileAndPassword(model.getMobile(), model.getPwd());
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User> op = userRepo.findByMobile(username);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	public Patient savePatient(PatientInfo pinfo) 
	{
		String pwd = Integer.toString(UtilFuns.generatePWD());
		Patient obj = new Patient(pinfo,pwd);
		obj = userRepo.save(obj);
		return obj;
	}
	
	public User getById(Integer id) {
		Optional<User> op = userRepo.findById(id);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}	
	
	public User findByMobile(String mobile) {
		Optional<User> op=userRepo.findByMobile(mobile);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}
}
