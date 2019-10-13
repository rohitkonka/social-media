package com.socialmedia.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.dao.SocialMediaDao;
import com.socialmedia.model.Post;

@Service
public class SocialMediaService {

	@Autowired
	SocialMediaDao mediaDao;

	public List<Post> getNewsFeed(String userId) {
		Set<String> followees = mediaDao.getFollowees(userId);
		followees.add(userId);
		return mediaDao.getNewsFeed(followees);
	}

	public void createPost(String userId, String content) {
		Post post = new Post();
		post.setCreationDate(new Date());
		post.setPostContent(content);
		post.setUserId(userId);
		mediaDao.createPost(post);
	}

	public void follow(String userId, String followeeId) {
		mediaDao.follow(userId, followeeId);
	}

	public void unfollow(String userId, String followeeId) {
		mediaDao.unfollow(userId, followeeId);
	}
}
