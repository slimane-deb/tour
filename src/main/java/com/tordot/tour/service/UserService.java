package com.tordot.tour.service;


import com.tordot.tour.entity.security.User;
import com.tordot.tour.entity.security.UserRole;
import com.tordot.tour.repository.RoleRepository;
import com.tordot.tour.repository.UserRepository;
import com.tordot.tour.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Set;

@Service
public class UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserSecurityService userSecurityService;

    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            localUser = userRepository.save(user);

            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
                ur.setUser(localUser);
                userRoleRepository.save(ur);
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
    }


    public void setUserSession (String username) {
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
