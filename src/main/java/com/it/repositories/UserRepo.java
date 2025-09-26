package com.it.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.entities.Receptionist;
import com.it.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
  List<Receptionist> findByRole(String role);
  Optional<User> findByMobileAndPassword(String mobile,String password);
  
  Optional<User> findByMobile(String mobile);
}
