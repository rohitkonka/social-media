package com.socialmedia.dao;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialmedia.dao.SocialMediaDao;
import com.socialmedia.model.Post;
import com.socialmedia.util.DataUtil;

@RunWith(SpringRunner.class)
public class SocialMediaDaoTest {

	@InjectMocks
	SocialMediaDao mediaDao;
	
	@Test
	public void getNewsFeedTest() {
		Post post = new Post();
		post.setCreationDate(new Date());
		post.setPostContent("test-content");
		post.setPostId(1L);
		post.setUserId("10001");
		mediaDao.createPost(post);
		assertTrue(DataUtil.getPosts(Collections.singleton("10001")).size() > 0);
	}

	@Test
	public void createPostTest() {
		Post post = new Post();
		post.setCreationDate(new Date());
		post.setPostContent("test-content");
		post.setPostId(1L);
		post.setUserId("10001");
		mediaDao.createPost(post);
		assertTrue(DataUtil.getPosts(Collections.singleton("10001")).size() > 0);
	}

	@Test
	public void followTest() {
		mediaDao.follow("10001", "10002");
		assertTrue(DataUtil.getFollowees("10001").contains("10002"));
	}

	@Test
	public void unfollowTest() {
		mediaDao.unfollow("10001", "10002");
		assertTrue(!DataUtil.getFollowees("10001").contains("10002"));
	}
}
