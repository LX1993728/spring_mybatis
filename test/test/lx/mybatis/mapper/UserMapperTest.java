package test.lx.mybatis.mapper;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.lx.mybatis.po.User;

public class UserMapperTest {

	// 会话工厂
		private ApplicationContext applicationContext;
		
		//创建工厂
		@Before
		public void init() throws IOException{
			// 创建Spring容器
			applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		}

	@Test
	public void testFindUserById() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

}
