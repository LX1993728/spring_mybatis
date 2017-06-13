package test.lx.mybatis.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.lx.mybatis.po.User;

public class UserDaoImplTest {
	// 会话工厂
	private ApplicationContext applicationContext;
	
	//创建工厂
	@Before
	public void init() throws IOException{
		// 创建Spring容器
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
	
	@Test
	public void testFindUserById() throws Exception{
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.findUserById(1);
		System.out.println(user);
	}
	
}
