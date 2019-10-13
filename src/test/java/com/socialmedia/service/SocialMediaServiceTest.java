package com.socialmedia.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialmedia.dao.SocialMediaDao;
import com.socialmedia.util.DataUtil;

@RunWith(SpringRunner.class)
public class SocialMediaServiceTest {

	@InjectMocks
	SocialMediaService mediaService;
	
	@Spy
	SocialMediaDao mediaDao;
	
	@Test
	public void getNewsFeedTest() {
		mediaService.createPost("10001", "test-post");
		assertTrue(mediaService.getNewsFeed("10001").size() > 0);
	}

	@Test
	public void createPost() {
		mediaService.createPost("10001", "test-post");
		assertTrue(mediaService.getNewsFeed("10001").size() >0);
	}

	@Test
	public void follow() {
		mediaService.follow("10001", "10002");
		assertTrue(DataUtil.getFollowees("10001").contains("10002"));
	}

	@Test
	public void unfollow() {
		mediaService.unfollow("10001", "10002");
		assertTrue(!DataUtil.getFollowees("10001").contains("10002"));
	}
}
