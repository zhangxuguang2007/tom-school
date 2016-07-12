package com.tom.school.test.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tom.school.test.service.controller.sys.AuthorityControllerTest;
import com.tom.school.test.service.controller.sys.SystemUserControlTest;

@RunWith(Suite.class)
@SuiteClasses({ AuthorityControllerTest.class, SystemUserControlTest.class })
public class AllTests {
	
}
