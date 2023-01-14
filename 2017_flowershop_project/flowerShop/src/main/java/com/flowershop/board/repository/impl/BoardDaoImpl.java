package com.flowershop.board.repository.impl;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flowershop.board.domain.BoardList;
import com.flowershop.board.domain.BoardVo;
import com.flowershop.board.repository.BoardDao;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insert(BoardVo vo) throws Exception {
		sqlSession.insert("board.write",vo);
	}

	@Override
	public int totalCount() throws Exception{
		return sqlSession.selectOne("board.totalCount");
	}

	@Override
	public ArrayList<BoardVo> getBoardlist(Map<String, Integer> map) throws Exception{
		return (ArrayList)sqlSession.selectList("board.getBoardlist", map);
	}

	@Override
	public void increment(int board_no) throws Exception {
		sqlSession.update("board.increment", board_no);
	}

	@Override
	public Object selectContent(int board_no) throws Exception {
		return sqlSession.selectOne("board.selectContent", board_no);
	}

	@Override
	public void incrementSeq(BoardVo vo) throws Exception {
		sqlSession.update("board.incrementSeq", vo);
	}

	@Override
	public void replyInsert(BoardVo vo) throws Exception {
		sqlSession.insert("board.replyInsert", vo);
	}

	@Override
	public void updateContent(BoardVo vo) throws Exception {
		sqlSession.update("board.updateContent", vo);
	}

	@Override
	public void deleteContent(int board_no) throws Exception {
		sqlSession.delete("board.deleteContent", board_no);
	}

	@Override
	public void incrementBRC(int board_no) throws Exception {
		sqlSession.update("board.incrementBRC", board_no);
	}

	@Override
	public int getBefore_ref(int board_no) throws Exception {
		return sqlSession.selectOne("board.getBefore_ref", board_no);
	}

	@Override
	public int getReply_count(int board_no) throws Exception {
		return sqlSession.selectOne("board.getReply_count", board_no);
	}

	@Override
	public void fixContent(int board_no) throws Exception {
		sqlSession.update("board.fixContent", board_no);
	}

	@Override
	public void incrementCommentCount(int board_no) throws Exception {
		sqlSession.update("board.incrementCommentCount", board_no);
	}

	@Override
	public void subBRC(int board_no) throws Exception {
		sqlSession.update("board.subBRC", board_no);
	}

	@Override
	public String getboard_no_userId(int board_no) throws Exception {
		return sqlSession.selectOne("board.getboard_no_userId", board_no);
	}

}
