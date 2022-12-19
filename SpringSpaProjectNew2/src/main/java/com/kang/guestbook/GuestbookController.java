package com.kang.guestbook;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
	
	@Autowired
	GuestbookDao dao;

	@RequestMapping("/guestbook/guestbook_select")
	public ModelAndView select(GPageVo pVo) {
		ModelAndView mv = new ModelAndView();
//		System.out.println(pVo.getFindStr());
		//service or dao
		//검색어에 따른 totSize를 가져와 page를 재계산. dao에서 해야 서드파티 개발자들이 편하다.
//		int totSize = dao.getTotSize(pVo);
		
		//검색어에 따른 List 가져옴
		List<GuestbookVo> list = dao.select(pVo);
//		for(GuestbookVo l : list) {
//			System.out.println(l.id);
//		}
		
		//List를 mv에 추가
		pVo = dao.getgVo(); //새로 갱신된 페이지 정보
		mv.addObject("pVo", pVo);
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook_select");
		return mv;
	}
	
	@RequestMapping("/guestbook/guestbook_insert")
	public String insert(GuestbookVo gVo, HttpServletResponse resp) {
		String msg = "";
		boolean b = dao.insert(gVo);
		if( !b ) msg = "저장 오류";
		return msg;
		//여기서 mapper를 불러도 상관없지만 유연성이나 확장성을 고려해서
		//dao에서 mapper를 부르는게 좋다.
		
//		try {
//			PrintWriter out = resp.getWriter();
//			
//			if( !b ) {
//				out.print("<script>");
//				out.print("    alert('저장 오류')");
//				out.print("</script>");
//			}
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@RequestMapping("/guestbook/guestbook_delete")
	public String delete(GuestbookVo gVo, HttpServletResponse resp) {
		String msg = "";
		boolean b = dao.delete(gVo);
		if( !b ) msg = "삭제 오류";
		return msg;
	}
	
	@RequestMapping("/guestbook/guestbook_update")
	public String update(GuestbookVo gVo, HttpServletResponse resp) {
		String msg = "";
		boolean b = dao.update(gVo);
		if( !b ) msg = "수정 오류";
		return msg;
	}
	
	@RequestMapping("/guestbook/guestbook10")
	public ModelAndView guestbook10() {
		ModelAndView mv = new ModelAndView();
		List<GuestbookVo> list = dao.guestbook10();
		
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook_10");
		return mv;
	}
	
}
