package com.tom.school.test.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tom.school.test.model.dao.sys.SystemUserDaoTest;

@RunWith(Suite.class)
@SuiteClasses({ SystemUserDaoTest.class })
public class AllTests {

}
