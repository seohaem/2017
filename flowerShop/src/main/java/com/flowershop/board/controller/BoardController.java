package com.flowershop.board.controller;

//주석 변경해보기 2017/09/26 배영철
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowershop.board.domain.BoardList;
import com.flowershop.board.domain.BoardVo;
import com.flowershop.board.service.BoardService;

@Controller
public class BoardController {
	
	private Log log = LogFactory.getLog(BoardController.class);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/write")
	private String writeFrom() throws Exception{
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/writeOK", method = RequestMethod.POST)
	private String writeInBoard(BoardVo vo) throws Exception {
		boardService.insert(vo);
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model)throws Exception {
		
	int pageNo = 1;
	try {
		pageNo = Integer.parseInt(request.getParameter("page"));
	} catch (Exception e) {}
	BoardList list = boardService.getBoardList(pageNo);
	
	model.addAttribute("R", list);
	model.addAttribute("pageNo", pageNo);
	return "board/list";
	}
}
