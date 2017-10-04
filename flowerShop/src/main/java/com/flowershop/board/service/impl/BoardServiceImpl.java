package com.flowershop.board.service.impl;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowershop.board.domain.BoardList;
import com.flowershop.board.domain.BoardVo;
import com.flowershop.board.repository.BoardDao;
import com.flowershop.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	@Override
	public void insert(BoardVo vo) throws Exception {
		boardDao.insert(vo);
	}

	@Override
	public BoardList getBoardList(int pageNo) throws Exception {
		int totalCount = boardDao.totalCount();					// 페이지 전체 글의 갯수 가져오기

		BoardList list = new BoardList(pageNo, totalCount); 	// 페이징 작업
		int startNo = list.getStartNo();
		int endNo = list.getEndNo();
		Map<String, Integer> map = new HashedMap();
		map.put("startNo", startNo);
		map.put("endNo", endNo);

		ArrayList<BoardVo> R = boardDao.getBoardlist(map);	// 실제 게시판 글 가져오
//		for (RboardVO vo : R) {
//			vo.setCommentCount(dao.commentCount(vo.getIdx())); // 각 글의 댓글 갯수를
//																// 불러온다.
//		}
		list.setList(R);
		return list;
	}

	@Override
	public void increment(int board_no) throws Exception{
		boardDao.increment(board_no);
	}

	@Override
	public BoardVo selectContent(int board_no) throws Exception {
		return (BoardVo) boardDao.selectContent(board_no);
	}

	@Override
	public void replyInsert(BoardVo vo) throws Exception {
//		System.out.println(vo.getBoard_no());
		int replyCount = boardDao.getReplyCount(vo);			 //  해당글의 답변글이 몇개인지 가져온다.
		vo.setBoard_seq(vo.getBoard_seq()+replyCount);			 //  해당글의 seq + replyCount 를 해준다 (이렇게 해야 댓글이 순서대로 들어간다.)
		boardDao.incrementSeq(vo);								 //  해당글의 답변이 들어갈 글의 위치를 비워두기 위해서 해당위치 의 글들의 seq + 1 을 모두 해준다.
		boardDao.replyInsert(vo);								 //  해당글의 답변글을 비워둔 자리에 넣어준다.
	}

	@Override
	public void update(BoardVo vo) throws Exception {
		boardDao.update(vo);
	}
	
	

	
}
