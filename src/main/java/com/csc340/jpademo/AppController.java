package com.csc340.jpademo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sunny
 */
@Controller
public class AppController {

  
    
    @GetMapping("/admin-page") // Map this to the URL where you want to serve the admin page
    public String adminPage() {
        return "user-list"; // Return the name of your HTML file (without the .html extension)
    }
}

