package com.samsolutions.kitayeu.myproject.services.impl;

import com.samsolutions.kitayeu.myproject.entities.Employee;
import com.samsolutions.kitayeu.myproject.entities.Role;
import com.samsolutions.kitayeu.myproject.entities.User;
import com.samsolutions.kitayeu.myproject.repositories.EmployeeRepository;
import com.samsolutions.kitayeu.myproject.repositories.UserRepository;
import com.samsolutions.kitayeu.myproject.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User readUser = userRepository.findUserByUserName(userName);

        if (readUser == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", userName));
        } else {
            Employee readEmployee = employeeRepository.findById(readUser.getUserId()).get();
            return new org.springframework.security.core.userdetails.User(readUser.getUserName(),
                    readUser.getUserPasswordHash(), mapRolesToAuthorities(readEmployee.getRole()));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
