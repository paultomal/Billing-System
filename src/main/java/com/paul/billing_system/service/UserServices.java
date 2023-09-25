package com.paul.billing_system.service;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
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

    private final SpecialistRepository specialistRepository;


    public UserServices(UserRepository userRepository, OrganizationRepository organizationRepository, SpecialistRepository specialistRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.specialistRepository = specialistRepository;
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

    public UserInfo saveOrgAdmin(Long id, UserInfoDTO userInfoDTO) {

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
    public UserInfo saveAdmin(Long id, UserInfoDTO userInfoDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(id);
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

            Specialist specialist1 = specialistRepository.findById(userInfoDTO.getSpId()).orElseThrow(RuntimeException::new);
            userInfo.setSpecialist(specialist1);
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

    public List<UserInfo> getAllAdmins(Long id, Long spId, PageRequest pageRequest) {

        Optional<Organization> organization = organizationRepository.findById(id);
        Optional<Specialist> specialist = specialistRepository.findById(spId);
        if (organization.isPresent())
            if (specialist.isPresent())
                return userRepository.findByOrganizationAndSpecialist(id,spId, pageRequest);
        return null;

    }
}
