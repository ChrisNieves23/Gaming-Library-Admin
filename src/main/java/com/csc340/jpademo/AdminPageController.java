
package com.csc340.jpademo;

/**
 *
 * @author chrisnieves
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminPageController {
    
    @GetMapping("/admin/index") // Map this to the URL where you want to serve the admin page
    public String adminPage() {
        return "admin/index"; // Return the name of your HTML file (without the .html extension)
    }
    
    /*
     @GetMapping("/admin/user/all") // Adjust the URL mapping as needed
public String userList(Model model) {
    // Add logic to fetch user data and pass it to the model if needed
    return "user/user-list"; // Assuming this is your user-list.html template
}
*/

@GetMapping("/admin/comment/all") // Adjust the URL mapping as needed
public String flaggedComments(Model model) {
    // Add logic to fetch user data and pass it to the model if needed
    return "comment/flagged-comments"; // Assuming this is your user-list.html template
}

}

