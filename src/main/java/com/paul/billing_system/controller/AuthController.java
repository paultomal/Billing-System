package com.paul.billing_system.controller;

import com.paul.billing_system.component.UserInfoUserDetailsService;
import com.paul.billing_system.dto.AuthRequestDTO;
import com.paul.billing_system.exception.ErrorDetails;
import com.paul.billing_system.security.JwtService;
import io.micrometer.common.util.internal.logging.InternalLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );

            if (authenticate.isAuthenticated()) {
                return new ResponseEntity(jwtService.generateToken(authRequestDTO.getUsername(),
                        (List) userDetailsService.loadUserByUsername(authRequestDTO.getUsername()).getAuthorities()),HttpStatus.OK);
            } else {
                throw new UsernameNotFoundException("Invalid user request!!");
            }
        } catch (AuthenticationException e) {
           String errorMessage = "Authentication failed: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(jwtService.extractRole(token.substring(7)), HttpStatus.OK);
    }


    //

    static ResponseEntity<?> getErrorDetails(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            String errorMessage = "Error: " + errorMessages;

            ErrorDetails errorDetails = new ErrorDetails(errorMessage);
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
        return null;
    }


/*    String errorMessage = null;

            if (e instanceof BadCredentialsException) {
        errorMessage = "Invalid username or password";
    }*/

    /*@PostMapping("/authenticate")
    public String authenticateGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDTO.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request!!");
        }
    }*/
}
