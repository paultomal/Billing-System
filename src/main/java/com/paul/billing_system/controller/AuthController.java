package com.paul.billing_system.controller;

import com.paul.billing_system.component.UserInfoUserDetailsService;
import com.paul.billing_system.dto.AuthRequestDTO;
import com.paul.billing_system.dto.ResponseDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.repository.UserRepository;
import com.paul.billing_system.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserInfoUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    public AuthController(JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserInfoUserDetailsService userDetailsService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateGetToken(@RequestBody AuthRequestDTO authRequestDTO) {

        createSysRoot();

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );
            if (authenticate.isAuthenticated()) {

                UserInfo user = userRepository.findByUsername(authRequestDTO.getUsername()).orElse(null);
                ResponseDTO responseDTO = new ResponseDTO();

                responseDTO.setToken(jwtService.generateToken(authRequestDTO.getUsername(),
                        (List) userDetailsService.loadUserByUsername(authRequestDTO.getUsername()).getAuthorities()));
                responseDTO.setUsername(authRequestDTO.getUsername());
                responseDTO.setRoles(jwtService.extractRole(responseDTO.getToken()));
                responseDTO.setExpiredDate(jwtService.extractExpiration(responseDTO.getToken()));
                assert user != null;
                responseDTO.setUserId(user.getId());
                responseDTO.setOrgCode(user.getOrganization().getOrgCode());
                responseDTO.setOrgId(user.getOrganization().getId());
                responseDTO.setOrgType(OrganizationTypes.getLabelByOrganizationType(user.getOrganization().getType()));

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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

    public void createSysRoot() {
        if (userRepository.findByUsername("root").isEmpty()) {
            Organization org = new Organization();
            org.setName("root");
            org.setOrgCode("root");
            org.setType(OrganizationTypes.ROOT);
            org.setAddress("Mohakhali");
            org.setEmail("jotno@gmail.com");

            UserInfo user = new UserInfo();
            user.setName("root");
            user.setUsername("root");
            user.setEmail("root@jotno.net");
            user.setPassword(passwordEncoder.encode("root"));
            user.setRoles(UserRoles.ROLE_ROOT);
            user.setOrganization(org);

            userRepository.save(user);
        }
    }
}
