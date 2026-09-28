package com.ddd.event_ticketing_platform.users.infrastructure;

import com.ddd.event_ticketing_platform.users.domain.repository.VenueManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class VenueManagerUserDetailsService implements UserDetailsService {

    private final VenueManagerRepository venueManagers;

    public VenueManagerUserDetailsService(VenueManagerRepository venueManagers) {
        this.venueManagers = venueManagers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return venueManagers.findByUsername(username);
    }
}
