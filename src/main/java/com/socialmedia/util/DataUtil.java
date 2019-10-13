package com.socialmedia.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.socialmedia.model.Post;

public final class DataUtil {
	private static Long postId = 0L;
	private static Set<Post> posts = new TreeSet<>();
	private static Map<String, Set<String>> followMap = new HashMap<>();

	private DataUtil() throws IllegalAccessException {
		throw new IllegalAccessException();
	}

	private static synchronized Long generatePostId() {
		return ++postId;
	}

	public static List<Post> getPosts(Set<String> followers) {
		return posts.stream().filter(p -> followers.contains(p.getUserId())).limit(20).collect(Collectors.toList());
	}

	public static void insertPost(Post post) {
		post.setPostId(generatePostId());
		posts.add(post);
	}

	public static Set<String> getFollowees(String userId) {
		return followMap.containsKey(userId) ? followMap.get(userId) : new HashSet<String>();
	}

	public static void follow(String userId, String followeeId) {
		Set<String> followees = followMap.containsKey(userId) ? followMap.get(userId) : new HashSet<String>();
		followees.add(followeeId);
		followMap.put(userId, followees);
	}

	public static void unfollow(String userId, String followeeId) {
		Set<String> followees = followMap.containsKey(userId) ? followMap.get(userId) : new HashSet<String>();
		followees.remove(followeeId);
		followMap.put(userId, followees);

	}
}
