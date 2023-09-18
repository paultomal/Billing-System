package com.paul.billing_system.service;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.repository.DepartmentRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OrganizationRepository organizationRepository;
    private final DepartmentRepository departmentRepository;


    public UserServices(UserRepository userRepository, OrganizationRepository organizationRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.departmentRepository = departmentRepository;
    }

    //Super Admin
    public UserInfo saveSuperAdmin(UserInfoDTO userInfoDTO){

        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoDTO.getName());
        userInfo.setUsername(userInfoDTO.getUsername());
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setPassword(userInfoDTO.getPassword());
        userInfo.setContact(userInfoDTO.getContact());
        UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ROOT");
                /*UserRoles.getUserRolesByLabel(userInfoDTO.getRoles());*/
        userInfo.setRoles(userRoles);
        userInfo = userRepository.save(userInfo);
        return userInfo;

    }


    //Admin

    public UserInfo saveAdmin(Long id, UserInfoDTO userInfoDTO) {

        Optional<Organization> organization = organizationRepository.findById(id);
        UserInfo userInfo = new UserInfo();

        if (organization.isPresent()) {
            userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ORG_ADMIN");
/*
                    UserRoles.getUserRolesByLabel(userInfoDTO.getRoles());
*/
            userInfo.setRoles(userRoles);
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
        return userInfo.orElseGet(UserInfo::new);
    }

    public UserInfo updateAdmin(Long id, UserInfoDTO userInfoDTO) {
        Optional<UserInfo> userInfo = userRepository.findById(id);
        if (userInfo.isPresent()) {
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

    // Staffs

    public UserInfo saveStaff(Long id, UserInfoDTO userInfoDTO) {
        Optional<Department> department = departmentRepository.findById(id);
        UserInfo userInfo = new UserInfo();
        if (department.isPresent()) {
            userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
            userInfo.setName(userInfoDTO.getName());
            userInfo.setUsername(userInfoDTO.getUsername());
            userInfo.setEmail(userInfoDTO.getEmail());
            userInfo.setPassword(userInfoDTO.getPassword());
            userInfo.setContact(userInfoDTO.getContact());
            UserRoles userRoles = UserRoles.getUserRolesByLabel("ROLE_ADMIN");
/*
                    UserRoles.getUserRolesByLabel(userInfoDTO.getRoles());
*/
            userInfo.setRoles(userRoles);
            userRepository.save(userInfo);
            department.get().getStaffs().add(userInfo);
            departmentRepository.save(department.get());
        }

        return userInfo;
    }
}
