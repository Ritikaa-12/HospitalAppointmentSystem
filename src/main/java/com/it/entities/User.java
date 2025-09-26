package com.it.entities;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="mobile",nullable=false,unique=true)
	private String mobile;
	
	@Column(name="pwd",nullable=false)
	@JsonIgnore
	private String password;
	
	@Column(name="active_status",nullable=false)
	@JsonIgnore
	private Boolean activeStatus;
	
	@Column(name="role",nullable=false)
	private String role;
	
	@Override
	public Collection<? extends GrantedAuthority>
	getAuthorities(){
		SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role);
		return Arrays.asList(sga);
		
	}
	@Override
	public String getUsername() {
		return mobile;
	}
}
