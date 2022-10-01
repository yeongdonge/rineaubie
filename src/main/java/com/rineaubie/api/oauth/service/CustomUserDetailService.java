package com.rineaubie.api.oauth.service;

import com.rineaubie.api.domain.user.User;
import com.rineaubie.api.oauth.entity.UserPrincipal;
import com.rineaubie.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException("계정명을 찾을 수 없습니다.");
        }
        return UserPrincipal.create(user);
    }
}
