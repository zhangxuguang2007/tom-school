package com.tom.school.utility.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.tom.school.core.bean.BeanUtility;

public class BeanUtilityTest {

	@Test
	public void testDescribleAvaliableParameter() {
		TestUser user = new TestUser();
		user.setId(Long.valueOf(100));
		user.setName("Tom");
		user.setPassword("Cogent01");
		user.set$like_email("%@126.com%");
		user.set$isNull_realName("%Xuguang%");
		try {
			Map<String, String> map = BeanUtility
					.describleAvaliableParameter(user);

			assertEquals(map.size(), 2);

			String emailKey = "$like_email";
			assertTrue(map.keySet().contains(emailKey));
			String emailValue = map.get(emailKey);
			assertEquals(emailValue, "%@126.com%");
			assertEquals(BeanUtility.getParamOpt(emailKey), "like");
			assertEquals(BeanUtility.getParamPropName(emailKey), "email");

			String realNameKey = "$isNull_realName";
			assertTrue(map.keySet().contains(realNameKey));
			String realNameValue = map.get(realNameKey);
			assertEquals(realNameValue, "%Xuguang%");
			assertEquals(BeanUtility.getParamOpt(realNameKey), "isNull");
			assertEquals(BeanUtility.getParamPropName(realNameKey), "realName");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class TestUser {
		
		private Long id;
		private String name;
		private String password;
		private String $like_email;
		private String $isNull_realName;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String get$like_email() {
			return $like_email;
		}

		public void set$like_email(String $like_email) {
			this.$like_email = $like_email;
		}

		public String get$isNull_realName() {
			return $isNull_realName;
		}

		public void set$isNull_realName(String $isNull_realName) {
			this.$isNull_realName = $isNull_realName;
		}

	}

}
