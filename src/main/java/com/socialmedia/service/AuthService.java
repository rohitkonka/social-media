package com.socialmedia.service;

import javax.security.sasl.AuthenticationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public void authenticateUser(String token) throws AuthenticationException {
		if(StringUtils.isEmpty(token))
			throw new AuthenticationException("user authentication failed");
	}

}
