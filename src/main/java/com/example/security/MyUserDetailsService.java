package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	public MyUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not exists"));
		return new User(user.getName(), user.getPassword(), user.isActive(), true, true, true, getAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
		String[] userRoles = user.getRoles().split(",");

		return AuthorityUtils.createAuthorityList(userRoles);
	}

}
