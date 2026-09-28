package com.ddd.event_ticketing_platform.users.infrastructure;

import com.ddd.event_ticketing_platform.users.domain.repository.BuyerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BuyerUserDetailsService implements UserDetailsService {

    private final BuyerRepository buyers;

    public BuyerUserDetailsService(BuyerRepository buyers) {
        this.buyers = buyers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buyers.findByUsername(username);
    }
}
