/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csc340.jpademo.login;

/**
 *
 * @author chrisnieves
 */
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate credentials
        if (isValidUser(username, password)) {
            String role = getUserRole(username);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            
            if ("admin".equals(role)) {
                response.sendRedirect("adminIndex.jsp");
            } else {
                response.sendRedirect("userIndex.jsp");
            }
        } else {
            response.sendRedirect("login.html"); // Redirect back to login page on failure
        }
    }

    private boolean isValidUser(String username, String password) {
        // Add your database validation logic here
        // Return true if the username and password are valid
        return true;
    }

    private String getUserRole(String username) {
        // Retrieve user role from the database based on username
        // Return the user's role (e.g., "admin", "user")
        return "admin"; // For demo purposes
    }
}
