package com.assignment.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.assignment.Entity.User;
import com.assignment.Repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = userRepository.findByEmail(username);

		if (opt.isPresent()) {

			User user = opt.get();

			List<GrantedAuthority> authorities = new ArrayList<>();

			return new UserDetailsImpl(user);

		} else {
			throw new BadCredentialsException("User details not found with username: " + username);
		}

	}
	
}
