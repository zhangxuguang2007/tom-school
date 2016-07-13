package com.tom.school.db.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tom.school.db.test.dao.sys.SystemUserDaoTest;

@RunWith(Suite.class)
@SuiteClasses({ SystemUserDaoTest.class })
public class AllTests {

}
