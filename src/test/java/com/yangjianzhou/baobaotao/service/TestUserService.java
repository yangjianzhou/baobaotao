package com.yangjianzhou.baobaotao.service;

import com.yangjianzhou.baobaotao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestUserService {

	@Autowired
	private UserService userService;

	@Test
	public void testHasMatchUser() throws Exception{
		boolean b1 = userService.hasMatchUser("yangjianzhou", "123456");
		boolean b2 = userService.hasMatchUser("admin", "1111");
		assertTrue(b1);
		assertTrue(!b2);
	}

	@Test
	public void testFindUserByUserName() throws Exception{
		User user = userService.findUserByUserName("yangjianzhou");
		assertEquals(user.getUserName(), "admin");
	}

	@Test
	public void testAddLoginLog() throws Exception{
		User user = userService.findUserByUserName("yangjianzhou");
		user.setUserId("sadasdqwee");
		user.setUserName("admin");
		user.setLastIp("192.168.12.7");
		user.setLastVisit(new Date());
		userService.loginSuccess(user);
	}
}
