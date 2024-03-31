package com.example.demo.controller;

import com.example.demo.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
    
    
    private void saveUserDataToJsonFile(User newUser) {
        try {
            // Define the path where you want to save the file
            String resourcesPath = System.getProperty("user.home") + "\\eclipse-workspace\\demo\\demo\\src\\main\\resources";
            String fileName = "users.json"; // A single file for all users
            File file = Paths.get(resourcesPath, fileName).toFile();

            // List to hold users
            List<User> users = new ArrayList<>();

            // Check if the file already exists and has content
            if (file.exists() && file.length() > 0) {
                // Read the existing content and convert back to list of User
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                User[] usersArray = objectMapper.readValue(content, User[].class); // Convert JSON array to User array
                users.addAll(Arrays.asList(usersArray)); // Add all existing users to list
            }

            // Add the new user to the list
            users.add(newUser);

            // Write the updated users list back to the file
            objectMapper.writeValue(file, users);
            System.out.println("Saved user data to " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
