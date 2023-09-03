package com.paul.billing_system.service;

import com.paul.billing_system.dto.AdminDTO;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo saveAdmin(AdminDTO adminDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(adminDTO.getName());
        userInfo.setEmail(adminDTO.getEmail());
        userInfo.setPassword(adminDTO.getPassword());
        userInfo.setRoles("Admin");
        return userRepository.save(userInfo);
    }

    public List<UserInfo> getAdmins() {
        return userRepository.findAll();
    }

    public UserInfo getAdminById(Long id) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        if (userInfo.isPresent())
            return userInfo.get();
        return new UserInfo();
    }

    public UserInfo updateAdmin(Long id, AdminDTO adminDTO) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()){
            UserInfo userInfo1 = userInfo.get();
            userInfo1.setName(adminDTO.getName());
            userInfo1.setEmail(adminDTO.getEmail());
            userInfo1.setPassword(adminDTO.getPassword());
            return userRepository.save(userInfo1);
        }
        return new UserInfo();
    }
}
