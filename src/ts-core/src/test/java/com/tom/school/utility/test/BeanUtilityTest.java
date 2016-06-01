package com.tom.school.utility.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.tom.school.utility.BeanUtility;

public class BeanUtilityTest {

	@Test
	public void testDescribleAvaliableParameter() {
		Student student = new Student();
		student.setId(Long.valueOf(100));
		student.setName("Tom");
		student.setPassword("Cogent01");
		student.set$email("zhangxuguang2007@126.com");
		student.set$realName("Xuguang");
		try {
			Map<String, String> map = BeanUtility
					.describleAvaliableParameter(student);
			
			assertEquals(map.size(), 2);
			
			assertTrue(map.keySet().contains("$email"));
			assertEquals(map.get("$email"), "zhangxuguang2007@126.com");
			
			assertTrue(map.keySet().contains("$realName"));
			assertEquals(map.get("$realName"), "Xuguang");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class Student {
		private Long id;
		private String name;
		private String password;
		private String $email;
		private String $realName;

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
		
		public String get$email() {
			return $email;
		}

		public void set$email(String $email) {
			this.$email = $email;
		}

		public String get$realName() {
			return $realName;
		}

		public void set$realName(String $realName) {
			this.$realName = $realName;
		}
	}

}
