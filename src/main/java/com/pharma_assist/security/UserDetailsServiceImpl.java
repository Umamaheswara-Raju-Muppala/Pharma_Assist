package com.pharma_assist.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pharma_assist.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AdminRepository adminRepository;

	public UserDetailsServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return adminRepository.findByAdminEmail(username).map(UserDetailsImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username Not Found!!.Failed to Authinticate."));

	}

}
