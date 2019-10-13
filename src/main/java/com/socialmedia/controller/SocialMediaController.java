package com.socialmedia.controller;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.model.Post;
import com.socialmedia.service.AuthService;
import com.socialmedia.service.SocialMediaService;

@RestController
@RequestMapping(value = "media")
public class SocialMediaController {

	@Autowired
	AuthService authService;
	
	@Autowired
	SocialMediaService mediaService; 
	
	@GetMapping(value = "/feeds/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Post> getNewsFeed( @RequestHeader String auth, @PathVariable String userId) throws AuthenticationException{
		authService.authenticateUser(auth);
		return mediaService.getNewsFeed(userId);
	}
	
	@PostMapping(value = "/feeds/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPost( @RequestHeader String auth, @PathVariable String userId, @RequestBody String content) throws AuthenticationException{
		authService.authenticateUser(auth);
		mediaService.createPost(userId, content);
	}
	
	@PostMapping(value = "/follow/{userId}/{followeeId}")
	@ResponseStatus(HttpStatus.OK)
	public void follow( @RequestHeader String auth, @PathVariable String userId, @PathVariable String followeeId) throws AuthenticationException{
		authService.authenticateUser(auth);
		mediaService.follow(userId, followeeId);
	}
	
	@DeleteMapping(value = "/unfollow/{userId}/{followeeId}")
	@ResponseStatus(HttpStatus.OK)
	public void unfollow( @RequestHeader String auth, @PathVariable String userId, @PathVariable String followeeId) throws AuthenticationException{
		authService.authenticateUser(auth);
		mediaService.unfollow(userId, followeeId);
	}
}
