package com.flowershop.admin.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.flowershop.admin.domain.RequestVo;
import com.flowershop.login.domain.UserVo;

public interface AdminDAO {
	
	//1:1문의 목록			
	public List<RequestVo> one_to_oneAll() throws Exception;
			
	//고객센터 문의 작성
	public void requestCreate(RequestVo vo) throws Exception;
			
	//1:1문의 상세보기
	public RequestVo requestRead(int request_no) throws Exception;
			
	//1:1문의 삭제
	public void requestDelete(int request_no) throws Exception;
			
	//1:1문의 수정
	public void requestUpdate(RequestVo vo) throws Exception;
	
	public List<UserVo> allMemberList() throws Exception;
	
	public void allListDelete(UserVo userVo) throws Exception;

	public UserVo memberDetail(UserVo userVo);

	public void memberDelete(UserVo userVo);

	public void memberDown(UserVo userVo);

	public void memberUp(UserVo userVo);
		
}
