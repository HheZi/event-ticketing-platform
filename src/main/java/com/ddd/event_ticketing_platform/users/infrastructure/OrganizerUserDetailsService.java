package com.ddd.event_ticketing_platform.users.infrastructure;

import com.ddd.event_ticketing_platform.users.domain.repository.OrganizerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OrganizerUserDetailsService implements UserDetailsService {

    private final OrganizerRepository organizers;

    public OrganizerUserDetailsService(OrganizerRepository organizers) {
        this.organizers = organizers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return organizers.findByUsername(username);
    }
}
