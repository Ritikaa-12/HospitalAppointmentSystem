package com.it.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.entities.Doctor;
import com.it.models.DoctorModel;
import com.it.repositories.DoctorRepo;

@Service
public class DoctorService {
@Autowired
private DoctorRepo repo;

public boolean saveDoctor(DoctorModel model) {
	try {
		Doctor doc=new Doctor(model);
		repo.save(doc);
		return true;
	}catch(Exception ex) {
		return false;
	}
}
public List<Doctor> list(){
	return repo.findAll();
}

public Doctor getById(Integer doctorid) {
	Optional<Doctor> op = repo.findById(doctorid);
	if(op.isPresent())
		return op.get();
	else
		return null;
}
}

