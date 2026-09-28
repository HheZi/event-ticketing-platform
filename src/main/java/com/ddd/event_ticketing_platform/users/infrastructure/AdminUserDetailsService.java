package com.ddd.event_ticketing_platform.users.infrastructure;

import com.ddd.event_ticketing_platform.users.domain.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository admins;

    public AdminUserDetailsService(AdminRepository admins) {
        this.admins = admins;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return admins.findByUsername(username);
    }

}
