package com.example.demo.controller;

import com.example.demo.entity.Player;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/add")
    public String addPlayer(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("age") int age,
                            @RequestParam("position") String position,
                            @RequestParam("height") int height,
                            @RequestParam("weight") int weight,
                            @RequestParam("nationality") String nationality,
                            @RequestParam("description") String description,
                            @RequestParam("picture") MultipartFile picture) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setAge(age);
        player.setPosition(position);
        player.setHeight(height);
        player.setWeight(weight);
        player.setNationality(nationality);
        player.setDescription(description);
        

        // Save player data to JSON file
        savePlayerDataToJsonFile(player);

        return "redirect:/Main.html"; 
    }

    private void savePlayerDataToJsonFile(Player player) {
        try {
            // Define the path where you want to save the file
        	String downloadsPath = System.getProperty("user.home") + "\\eclipse-workspace\\demo\\demo\\src\\main\\resources";
        	
        	// Generating a unique file name using the current timestamp
            String fileName = "player_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + ".json";
        	
        	File file = Paths.get(downloadsPath, fileName).toFile();
        	
            
            // Write the player object as JSON to the file
            objectMapper.writeValue(file, player);
            System.out.println("Saved player data to " + file.getAbsolutePath());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}