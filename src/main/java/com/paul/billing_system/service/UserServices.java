package com.paul.billing_system.service;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OrganizationRepository organizationRepository;


    public UserServices(UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    //Super Admin
    public UserInfo saveSuperAdmin(UserInfoDTO userInfoDTO) {

        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoDTO.getName());
        userInfo.setUsername(userInfoDTO.getUsername());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPassword(userInfoDTO.getPassword());
        userInfo.setContact(userInfoDTO.getContact());
        UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ROOT");
        userInfo.setRoles(userRoles);
        userInfo = userRepository.save(userInfo);
        return userInfo;

    }


    //Org Admin

    public UserInfo saveOrgAdmin(UserInfoDTO userInfoDTO) {

        Optional<Organization> organization = organizationRepository.findById(userInfoDTO.getOrgId());
        UserInfo userInfo = new UserInfo();

        if (organization.isPresent()) {
            userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ORG_ADMIN");

            Organization organization1 = organizationRepository.findById(userInfoDTO.getOrgId()).orElseThrow(RuntimeException::new);
            userInfo.setOrganization(organization1);

            userInfo.setRoles(userRoles);
            userRepository.save(userInfo);
        }

        return userInfo;
    }

    public List<UserInfo> getOrgAdmins(Long id, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent())
            return userRepository.findByOrganization(id, pageRequest);
        return null;
    }

    public UserInfo getOrgAdminById(Long id) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        return userInfo.orElseGet(UserInfo::new);
    }

    public UserInfo updateOrgAdmin(Long id, UserInfoDTO userInfoDTO) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()) {
            userInfo.get().setName(userInfoDTO.getName());
            userInfo.get().setUsername(userInfoDTO.getUsername());
            userInfo.get().setEmail(userInfoDTO.getEmail());
            userInfo.get().setContact(userInfoDTO.getContact());
            userInfo.get().setUpdatedAt(new Date());

            return userRepository.save(userInfo.get());
        }
        return null;
    }

    // Staffs

    @Transactional
    public UserInfo saveAdmin(UserInfoDTO userInfoDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(userInfoDTO.getOrgId());
        UserInfo userInfo = new UserInfo();
        if (organization1.isPresent()) {
            userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ADMIN");
            userInfo.setRoles(userRoles);

            Organization organization = organizationRepository.findById(userInfoDTO.getOrgId()).orElseThrow(RuntimeException::new);
            userInfo.setOrganization(organization);

            userRepository.save(userInfo);
        }

        return userInfo;
    }

    public UserInfo getAdminById(Long id) {
        Optional<UserInfo> specialist = userRepository.findById(id);
        return specialist.orElse(null);
    }

    public UserInfo updateAdmin(UserInfoDTO userInfoDTO, Long id) {

        Optional<UserInfo> userInfo = userRepository.findById(id);

        if (userInfo.isPresent()) {
            userInfo.get().setName(userInfoDTO.getName());
            userInfo.get().setUsername(userInfoDTO.getUsername());
            userInfo.get().setEmail(userInfoDTO.getEmail());
            userInfo.get().setContact(userInfoDTO.getContact());
            userInfo.get().setUpdatedAt(new Date());

            return userRepository.save(userInfo.get());
        }
        return null;
    }

    public List<UserInfo> getAllAdmins(Long id, PageRequest pageRequest) {

        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent())
            return userRepository.findByOrganizationAndSpecialist(id, pageRequest);
        return null;

    }
}
