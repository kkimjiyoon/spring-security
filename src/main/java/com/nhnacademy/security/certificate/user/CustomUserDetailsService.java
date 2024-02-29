package com.nhnacademy.security.certificate.user;

import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class CustomUserDetailsService implements UserDetailsService {

    private final ResidentRepository residentRepository;

    public CustomUserDetailsService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String residentId) throws UsernameNotFoundException {
        Resident resident = residentRepository.findByResidentId(residentId);
        if (Objects.isNull(resident)) {
            throw new UsernameNotFoundException(residentId + " not found");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_MEMBER"); // 임의로 권한을 member로 설정
        Collection<GrantedAuthority> grantedAuthorities = Collections.singletonList(grantedAuthority);

        return new User(residentId, resident.getResidentPassword(), grantedAuthorities);
    }
}
