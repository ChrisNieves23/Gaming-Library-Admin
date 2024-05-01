
package com.csc340.jpademo.user;

/**
 *
 * @author chrisnieves
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
   
    User findByUsername(String username);
    
 //   List<User> findByUsernameContaining(String keyword);
    
    
     List<User> findByUsernameIgnoreCaseContaining(String keyword);
}





