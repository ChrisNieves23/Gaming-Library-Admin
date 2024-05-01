
package com.csc340.jpademo.user;

/**
 *
 * @author chrisnieves
 */
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
      public void saveUser(User user) {
        userRepository.save(user);
    }
    
     public List<User> getAll() {
        return userRepository.findAll();
    }
     public User getUserById(Long user_Id) {
        return userRepository.findById(user_Id).orElse(null);
    }

    public User editUserRole(Long user_Id, String newRole) {
        User admin = userRepository.findById(user_Id).orElse(null);
        if (admin != null) {
            admin.setRole(newRole);
            return userRepository.save(admin);
        }
        return null; // Or throw an exception as per your needs
    }

    public void deleteUser(Long user_Id) {
        userRepository.deleteById(user_Id);
    }

    public List<User> searchUsers(String keyword) {
        // Implement search logic using Spring Data JPA query method
        return userRepository.findByUsernameIgnoreCaseContaining(keyword);
    }
    
    public Optional<User> searchUsersId(Long user_Id) {
        // Implement search logic using Spring Data JPA query method
        return userRepository.findById(user_Id);
    }
    
    
       
    public String getUserRole(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getRole();
        }
        return null; // Or throw an exception indicating user not found
    }
    
     public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
     
    public boolean isUsernameTaken(String username) {
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }
     
}
