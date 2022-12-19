package com.kang.board;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FileUploadController {
	static String path = "C:\\Users\\dkapq\\eclipse-workspace\\SpringSpaProjectNew\\src\\main\\resources\\static\\upload\\";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile") List<MultipartFile> mul,
						 @ModelAttribute BoardVo vo,  @ModelAttribute PageVo pVo) {
		
		String msg="";
		
		try {
			
			// 본문을 저장
			boolean flag = service.insertR(vo);
			
			if(flag) {
					for(MultipartFile m : mul) {
						if(!m.isEmpty()) {
							List<AttVo> attList = new ArrayList<AttVo>();
							
							attList = fileupload(mul);
							service.insertAttList(attList);
					}
				}
			}else {
				msg="fail";
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		//msg="확인";
		return msg;
	}
	
	@RequestMapping("/board/board_updateR")
	public ModelAndView updateR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo,
			@RequestParam(name = "delFile",required = false) String[] delFile) {
		System.out.println("delFile : " + Arrays.toString(delFile));
		String msg="";
		try {
			for(MultipartFile m : mul)
				if(!m.isEmpty()) {
					List<AttVo> attList = new ArrayList<AttVo>();
					bVo.setAttList(attList);
					
					attList = fileupload(mul);
					service.insertAttList(attList);
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		boolean flag = service.updateR(bVo, delFile);
		if( !flag ) msg="수정중 오류 발생";
		
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.select(pVo);
		mv.addObject("pVo", pVo);
		mv.addObject("list", list);
		mv.setViewName("board/board_select");
		return mv;
		
	}
	
	@RequestMapping("/board/board_replR")
	public synchronized ModelAndView replR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) {
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			attList = fileupload(mul);
			bVo.setAttList(attList);
			
			boolean flag = service.replR(bVo);
			//if( !flag ) return "수정중 오류 발생";
			

			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = service.select(pVo);
		mv.addObject("pVo", pVo);
		mv.addObject("list", list);
		mv.setViewName("board/board_select");
		return mv;
	}
	
	//file upload 공통부분(insertR, updateR, del
	public List<AttVo> fileupload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue;
			
			UUID uuid = UUID.randomUUID();
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
			
		}
		
		return attList;
	}
}
