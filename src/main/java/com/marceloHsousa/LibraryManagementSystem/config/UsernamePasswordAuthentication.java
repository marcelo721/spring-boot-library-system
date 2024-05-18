package com.marceloHsousa.LibraryManagementSystem.config;

import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePasswordAuthentication implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthentication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        List<User> list=userRepository.findByEmail(username);
        if(list.size()>0){
            if (passwordEncoder.matches(password, list.get(0).getPassword())){
                return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities(list.get(0).getRole()));
            }else{
                throw new BadCredentialsException("Invalid password");
            }
        }else{
            throw new BadCredentialsException("No user found with this email");
        }
    }

    private List<GrantedAuthority> grantedAuthorities(String role){
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
