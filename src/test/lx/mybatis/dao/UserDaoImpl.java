package test.lx.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import test.lx.mybatis.po.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public User findUserById(int id) throws Exception {
		// 创建SqlSession
		SqlSession sqlSession = this.getSqlSession();

		// 根据id查询用户信息
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}
}
