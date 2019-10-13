package com.socialmedia.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmedia.model.Post;
import com.socialmedia.util.DataUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SocialMediaController.class)
public class MediaControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void testInsertPost() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/media/feeds/10001").contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("auth", getMockAuth())
				.content(mapper.writeValueAsString("test-content"));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(201, result.getResponse().getStatus());
	}
	
	@Test
	public void testInsertPostWithoutAuth() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/media/feeds/10001").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString("test-content"));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetFeeds() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/media/feeds/10001")
				.header("auth", getMockAuth());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		Post[] data = mapper.readValue(result.getResponse().getContentAsString(), Post[].class);
		assertTrue(data.length < 20);
	}
	
	@Test
	public void testFollow() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/media/follow/10001/10002")
				.header("auth", getMockAuth());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(DataUtil.getFollowees("10001").contains("10002"), true);
	}
	
	@Test
	public void testUnfollow() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/media/unfollow/10001/10002")
				.header("auth", getMockAuth());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(DataUtil.getFollowees("10001").contains("10002"), false);
	}
	
	private static String getMockAuth() {
		return "mock-header";
	}
}
