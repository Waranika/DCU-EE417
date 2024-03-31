package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
    private String username;
    private String email;
    private String password; 
    private String phone;
    private String fullname;
    private String address;
    private Boolean twoFactorAuth;
    private LocalDate dob;
    private String preferredLanguage;
	
	
	// Constructor
	public User() {};
	
	
	// Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullname() {
    return fullname;
	}

	public void setFullname(String fullname) {
    this.fullname = fullname;
	}	
	
	public String getAddress() {
	return address;
		}

	public void setAddress(String address) {
	this.address = address;
		}	
	
	
	public Boolean getTwoFactorAuth() {
	return twoFactorAuth;
	}

	public void setTwoFactorAuthString (Boolean twoFactorAuth) {
	this.twoFactorAuth = twoFactorAuth;
	}	
	
	public LocalDate getDob() {
	return dob;
	}

	public void setDob(LocalDate dob) {
	this.dob = dob;
	}	
	
	public String getPreferredLanguage() {
	return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
	this.preferredLanguage = preferredLanguage;
	}
	
	public String getPhone() {
	return phone;
	}

	public void setPhone(String phone) {
	this.phone = phone;
	}
}
