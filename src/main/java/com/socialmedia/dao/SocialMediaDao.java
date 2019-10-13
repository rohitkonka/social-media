package com.socialmedia.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.socialmedia.model.Post;
import com.socialmedia.util.DataUtil;

@Repository
public class SocialMediaDao {

	public List<Post> getNewsFeed(Set<String> followees) {
		return DataUtil.getPosts(followees);
	}

	public void createPost(Post post) {
		DataUtil.insertPost(post);
	}
	
	public void follow(String userId, String followeeId) {
		DataUtil.follow(userId,followeeId);
	}

	public void unfollow(String userId, String followeeId) {
		DataUtil.unfollow(userId,followeeId);
	}

	public Set<String> getFollowees(String userId) {
		return DataUtil.getFollowees(userId);
	}
}
