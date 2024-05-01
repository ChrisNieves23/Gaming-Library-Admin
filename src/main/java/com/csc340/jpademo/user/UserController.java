
package com.csc340.jpademo.user;

/**
 *
 * @author chrisnieves
 */
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
     
    @GetMapping("/all")
    public String getUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("userList", userList);
        return "user/user-list"; // Assumes there is a user/user-list.html Thymeleaf template
    }

    @PutMapping("/edit-role/{user_id}")
    public String showEditUserRoleForm(@PathVariable("user_id") Long userId, Model model) {
        // Assuming userService.getUserById(userId) fetches the user information by ID
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/edit-role"; // Assuming there is a user/edit-role.html Thymeleaf template
    }

    @PostMapping("/edit-role/{user_id}")
    public String editUserRole(@PathVariable("user_id") Long userId, @RequestParam String newRole, Model model) {
        userService.editUserRole(userId, newRole); // Update user role
        return "redirect:/user/all"; // Redirect back to user list
    }

    @DeleteMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable("user_id") Long userId, Model model) {
        userService.deleteUser(userId);
        return "redirect:/user/all"; // Redirect back to the user list page
    }

   @GetMapping("/searchByUsername")
    public String searchUsersByUsername(@RequestParam String keyword, Model model) {
        List<User> searchResults = userService.searchUsers(keyword);
        model.addAttribute("userList", searchResults);
        return "user/user-list"; // Render the user list page with search results
    }
    
    @GetMapping("/searchById")
    public String searchUsersById(@RequestParam Long userId, Model model) {
        User user = userService.getUserById(userId);
        List<User> searchResults = new ArrayList<>();
        if (user != null) {
            searchResults.add(user);
        }
        model.addAttribute("userList", searchResults);
        return "user/user-list"; // Render the user list page with search results
    }
    
    
    
    
    
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login/login"; // Assuming there is a login.html Thymeleaf template
    }
    
    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate credentials against the database
        boolean isValidUser = userService.validateUser(username, password);

        if (isValidUser) {
            // Assuming userService.getUserRole(username) fetches the user's role
            String role = userService.getUserRole(username);
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("role", role);

            if ("admin".equals(role)) {
                return "redirect:/admin/index"; // Redirect to admin page
            } else {
                return "redirect:/user"; // Redirect to regular user page
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login/login"; // Redirect back to login page with an error message
        }
    }
    
    @GetMapping("/admin/index")
    public String adminPage() {
        // Check if the user is logged in as admin; if not, redirect to login
        // You can implement this logic based on session attributes
        return "admin/index"; // Assuming there is an admin.html Thymeleaf template
    }
    
     @GetMapping("/user")
    public String userPage() {
        // Check if the user is logged in as a regular user; if not, redirect to login
        // You can implement this logic based on session attributes
        return "user"; // Assuming there is a user.html Thymeleaf template
    }
    
    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        request.getSession().invalidate(); // Invalidate the session
        return "redirect:/"; // Redirect to the login page after logout
    }
    
    
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "login/register"; // Assuming there is a register.html Thymeleaf template
    }
    
     private final AtomicLong idCounter = new AtomicLong(); // Initialize a counter

    // Other methods

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,
            @RequestParam String email, @RequestParam String name, @RequestParam String lastname,
            @RequestParam(required = false) String code, Model model) {
        
        
        
        if (userService.isUsernameTaken(username)) {
        model.addAttribute("error", "Username already exists. Please choose a different username.");
        return "login/register"; // Redirect back to the registration form with an error message
    }
        
        // Generate a unique user ID
        long user_id = generateUserId();

        // Determine user role based on code
        String role = determineUserRole(code);
        
        
        
        // Create a new user object with the generated user ID
        User newUser = new User(user_id, email, lastname, name, password, role, username);

        // Save the user
        userService.saveUser(newUser);
        
        // Redirect to login page after successful registration
        return "redirect:/user/login"; // Redirect to login.html
    }

    private long generateUserId() {
        return idCounter.incrementAndGet(); // Increment and get the next unique ID
    }

    private String determineUserRole(String code) {
        // Logic to determine user role based on code
        if (code != null && !code.isEmpty()) {
            if ("adminCode".equals(code)) {
                return "admin";
            } else if ("publisherCode".equals(code)) {
                return "publisher";
            }
        }
        return "user"; // Default role if no code or invalid code
    }


    
    
    
}

