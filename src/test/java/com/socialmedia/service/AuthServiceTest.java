package com.socialmedia.service;


import javax.security.sasl.AuthenticationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthServiceTest {
	
	@InjectMocks
	AuthService authService;
	
	@Test(expected = AuthenticationException.class)
	public void testAuthServiceException() throws Exception {
		authService.authenticateUser(null);
	}
	
	@Test
	public void testAuthService() throws Exception {
		authService.authenticateUser("test-token");
	}
}
