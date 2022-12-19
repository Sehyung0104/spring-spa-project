package com.kang.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_select")
	public ModelAndView select(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.select(pVo);
		pVo = service.getpVo();
		
		mv.addObject("pVo", pVo);
		mv.addObject("list", list);
		mv.setViewName("board/board_select");
		return mv;
	}
	
	@RequestMapping("/board/board10")
	public ModelAndView board10() {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.board10();
		
		mv.addObject("list", list);
		mv.setViewName("board/board_10"); // /있어도 되고 없어도 된다
		return mv;
	}
	
	@RequestMapping("/board/board_view")
	public ModelAndView view(PageVo pVo, BoardVo bVo) {
		ModelAndView mv = new ModelAndView();
		
		//조회수 증가
		bVo = service.view(bVo.getSno(), "up");
		/* 
		 * textarea에서는 <br/>이 엔터고
		 * db에서는 \n이 엔터다.
		 * 서로 어떻게 호환이 되고 있는지 과정은 확실히 모르겠다.
		 */
		//doc안에 있는 \n 기호를 <br/>로 변경
		String temp = bVo.getDoc();
		bVo.setDoc(bVo.getDoc().replace("\n","<br/>"));
		
		mv.addObject("pVo", pVo);
		mv.addObject("bVo", bVo);
		
		mv.setViewName("board/board_view");
		return mv;
	}
	@RequestMapping("/board/board_delete")
	public ModelAndView delete(BoardVo bVo, PageVo pVo) {
		String msg="";
		ModelAndView mv = new ModelAndView();
		// 조회수 증가
		boolean b = service.delete(bVo);
		if(!b) {
			msg = "삭제중 오류 발생";
		}
		mv = select(pVo);
		mv.addObject("msg", msg);
		//mv.addObject("pVo", pVo);
		mv.setViewName("/board/board_select");
		return mv;
	}
	
	@RequestMapping("/board/board_insert")
	public ModelAndView insert(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pVo", pVo);
		mv.setViewName("/board/board_insert");
		return mv;
	}
	
	@RequestMapping("/board/board_update")
	public ModelAndView update(PageVo pVo) {
		ModelAndView mv = new ModelAndView();
		BoardVo bVo = service.view(pVo.getSno(),"");
		mv.addObject("bVo", bVo);
		mv.addObject("pVo", pVo);
		mv.setViewName("/board/board_update");
		return mv;
	}
	
	@RequestMapping("/board/board_repl")
	public ModelAndView repl(PageVo pVo, BoardVo bVo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("bVo", bVo);
		mv.addObject("pVo", pVo);
		mv.setViewName("/board/board_repl");
		return mv;
	}

}
