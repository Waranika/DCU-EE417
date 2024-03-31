package com.example.demo.controller;

import com.example.demo.entity.User;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    
    
    
    @PostMapping("/modify")
    public String addPlayer(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("fullname") String fullname,
            @RequestParam("address") String address,
            
            @RequestParam(name = "twoFactorAuth", defaultValue = "false") Boolean twoFactorAuth,
            @RequestParam("dob") LocalDate dob,
    		@RequestParam("preferredLanguage") String preferredLanguage)
    {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setFullname(fullname);
        user.setAddress(address);
        user.setTwoFactorAuthString(twoFactorAuth);
        objectMapper.registerModule(new JavaTimeModule());
        user.setDob(dob);
        user.setPreferredLanguage(preferredLanguage);


        
        // Save user data to JSON file
        saveUserDataToJsonFile(user);

        return "redirect:/Main.html"; 
    }
    
    
    private void saveUserDataToJsonFile(User user) {
        try {
        	 // Define the path where you want to save the file
        	String downloadsPath = System.getProperty("user.home") + "\\eclipse-workspace\\demo\\demo\\src\\main\\resources";
        	
            // Generating a unique file name using the type and current timestamp
            String fileName =  "User_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + ".json";
            
            File file = Paths.get(downloadsPath, fileName).toFile();
            
         // Write the User object as JSON to the file
            objectMapper.writeValue(file, user);
            System.out.println("Saved player data to " + file.getAbsolutePath());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
