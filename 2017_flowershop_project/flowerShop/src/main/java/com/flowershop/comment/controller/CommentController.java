package com.flowershop.comment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flowershop.board.service.BoardService;
import com.flowershop.comment.domain.CommentVo;
import com.flowershop.comment.service.CommentService;
import com.flowershop.login.domain.UserVo;


@Controller
public class CommentController {

	
	private Log log = LogFactory.getLog(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/commentOK") // 댓글의 입력을 수행한다.
	public String commentOK(CommentVo commentVo, HttpServletRequest request, Model model)throws Exception {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int board_no = Integer.parseInt(request.getParameter("board_no"));
//		int comment_scryn = Integer.parseInt(request.getParameter("comment_scryn"));
//		commentVo.setComment_scryn(comment_scryn);
		commentService.insertComment(commentVo);
		commentService.incrementCommentCount(board_no);

		model.addAttribute("board_no", board_no);
		model.addAttribute("pageNo", pageNo);

		return "redirect:content_view"; // 메시지를 alert 할 수 있는 jsp파일로 이동한다.
		// return "redirect:view"; // @RequestMapping("/view") 메소드를 호출한다.
	}
	
	@RequestMapping(value="/commentReplyInsert", method=RequestMethod.PUT) // 댓글의 입력을 수행한다.
	@ResponseBody
	public String commentReplyInsert(HttpServletRequest request, Model model, HttpSession session)throws Exception {
		String comment_content = request.getParameter("comment_content");
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		int comment_scryn = 0;
		if(request.getParameter("comment_scryn") != null) {
			comment_scryn = Integer.parseInt(request.getParameter("comment_scryn"));
		}
		System.out.println(comment_scryn);
		CommentVo commentVo = commentService.getCommentVo(comment_no);
		commentVo.setComment_scryn(comment_scryn);
		commentVo.setUser_id(userVo.getUser_id());
		commentVo.setComment_content(comment_content);
		
		commentService.commentReplyInsert(commentVo);
		commentService.incrementCommentCount(commentVo.getBoard_no());

		model.addAttribute("board_no", commentVo.getBoard_no());

		return "ok"; // 메시지를 alert 할 수 있는 jsp파일로 이동한다.
	}
	
	
	@RequestMapping(value="/commentUpdate", method=RequestMethod.PUT)
	@ResponseBody
	public String QreplyOneUpdate(@RequestParam int comment_scryn, @RequestParam int comment_no, @RequestParam String comment_content)throws Exception{ 
		
		Map<String, String> map = new HashMap<>();
		map.put("comment_no", comment_no+"");
		map.put("comment_scryn", comment_scryn+"");
		map.put("comment_content", comment_content);
		commentService.updateComment(map);
		
		return "ok";
	}
	
	
	@RequestMapping(value="/commentDelete", method=RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<String> del_co(HttpServletRequest request, Model model)throws Exception{
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		int comment_reply_count = Integer.parseInt(request.getParameter("comment_reply_count"));
		ResponseEntity<String> entity = null;
		
		try{
			commentService.del_comment(comment_no, board_no, comment_reply_count);
			entity = new ResponseEntity<String>("ok", HttpStatus.OK); 
		} catch (Exception e){ // 에러처리
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); //에러 발생시 
		}
		return entity;
	}
	
	@RequestMapping(value="/commentList", method=RequestMethod.POST)
	public String commentList(HttpServletRequest request ,Model model, HttpSession session)throws Exception{
		if(session.getAttribute("authUser") == null) {
			model.addAttribute("msg", "로그인 후 사용 가능합니다.");
			model.addAttribute("url", "login");
			return"board/alert"; 
		}
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		ArrayList<CommentVo> commentList = commentService.selectComentList(board_no);	
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String board_no_userId = boardService.getboard_no_userId(board_no);
		model.addAttribute("board_no_userId", board_no_userId);
		model.addAttribute("userVo", userVo);
		model.addAttribute("commentList", commentList);
		model.addAttribute("board_no", board_no);
		
		return "board/commentList";
	}
	
	
}







