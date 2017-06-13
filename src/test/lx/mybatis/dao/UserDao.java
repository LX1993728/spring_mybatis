package test.lx.mybatis.dao;

import java.util.List;

import test.lx.mybatis.po.User;

/**
 * 用户DAO
 * 
 * @author lx
 * 
 */
public interface UserDao {
	// 根据id查询用户信息
	public User findUserById(int id) throws Exception;

}
