package com.flowershop.login.repository.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flowershop.login.domain.UserVo;
import com.flowershop.login.repository.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserVo login(String user_id) throws Exception {
		return sqlSession.selectOne("login.userLogin", user_id);
	}

	@Override
	public void keepLogin(String user_id, String sessionId, Date next) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		sqlSession.update("login.keepLogin", paramMap);
	}

	@Override
	public UserVo checkUserWithSessionKey(String value) {
		return sqlSession.selectOne("login.checkUserWithSessionKey", value);
	}

	@Override
	public UserVo myInfo(String user_id) throws Exception {
		return sqlSession.selectOne("login.myInfo", user_id);
	}

	public void changeInfo(UserVo userVo) throws Exception {
		sqlSession.update("login.changeInfo", userVo);
	}

	@Override
	public void changePw(String user_pw, String user_id) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("user_pw", user_pw);
		sqlSession.update("login.changePw", paramMap);
	}

	@Override
	public UserVo findUserId(String user_email) throws Exception {
		return sqlSession.selectOne("login.findUserId", user_email);
	}

	@Override
	public UserVo findUserPw(String user_id, String user_email) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("user_email", user_email);
		return sqlSession.selectOne("login.findUserPw", paramMap);
	}

	public void userPwInit(String user_id, String user_pw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("user_pw", user_pw);
		sqlSession.update("login.userPwInit", paramMap);
	}

}
