package com.paul.billing_system.service;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public UserServices(UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }
    //Super Admin
    public UserInfo saveSuperAdmin( UserInfoDTO userInfoDTO) {

        UserInfo userInfo = new UserInfo();
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            userInfo.setRoles("Super_Admin");
            return userRepository.save(userInfo);
    }


    //Admin

    public UserInfo saveAdmin(Long id, UserInfoDTO userInfoDTO) {

        Optional<Organization> organization = organizationRepository.findById(id);
        UserInfo userInfo = new UserInfo();

        if (organization.isPresent()){
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            userInfo.setRoles("Admin");
            userRepository.save(userInfo);
            organization.get().getAdmins().add(userInfo);
            organizationRepository.save(organization.get());
        }

        return userInfo;
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

    public UserInfo updateAdmin(Long id, UserInfoDTO userInfoDTO) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()){
            UserInfo userInfo1 = userInfo.get();
            userInfo1.setName(userInfoDTO.getName());
            userInfo1.setUsername(userInfoDTO.getUsername());
            userInfo1.setEmail(userInfoDTO.getEmail());
            userInfo1.setPassword(userInfoDTO.getPassword());
            userInfo1.setContact(userInfoDTO.getContact());
            return userRepository.save(userInfo1);
        }
        return new UserInfo();
    }
}
