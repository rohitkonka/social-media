package com.socialmedia.model;

import java.util.Date;

public class Post implements Comparable<Post>{
	private String userId;
	private String postContent;
	private Date creationDate;
	private Long postId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	@Override
	public int compareTo(Post post) {
		int compare = post.getCreationDate().compareTo(this.getCreationDate());
		if(post.getCreationDate() == null || compare == 0)
			return -1;
		else return compare;
	}
}
