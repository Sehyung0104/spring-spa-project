 package com.kang.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Transactional
@Service
public class BoardService {
	
	PageVo pVo;
	
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	PlatformTransactionManager manager;
	
	TransactionStatus status;
	
	@Autowired
	BoardMapper boardMapper;
	Object savePoint;
	
	public boolean insertR(BoardVo bVo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		boolean b = true;
		
		int cnt = boardMapper.insertR(bVo);
		System.out.println("cnt : " + cnt);
		if(cnt < 1) {
			status.rollbackToSavepoint(savePoint);
			b=false;
		}else {
			manager.commit(status);
		}
		
		
//		else if(bVo.getAttList().size()>0) {
//			int attCnt = boardMapper.insertAttList(bVo.getAttList());
//			if(attCnt<0) b=false;
//			manager.commit(status);
//		}
//		//if(b) manager.commit(status);
//		else {
//			status.rollbackToSavepoint(savePoint);
//			
//			String[] delFiles = new String[bVo.getAttList().size()];
//			for(int i=0; i<bVo.getAttList().size(); i++) {
//				delFiles[i] = bVo.getAttList().get(i).getSysFile();
//			}
//			fileDelete(delFiles);
//		}
		return b;
	}
	
	public void insertAttList(List<AttVo> attList) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		int cnt = boardMapper.insertAttList(attList);
		
		if(cnt>0) {
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
	}
	
	public List<BoardVo> select(PageVo pVo) {
		int totSize = mapper.totList(pVo);
		pVo.setTotSize(totSize);
		this.pVo = pVo;
		List<BoardVo> list = mapper.select(pVo);
		return list;
	}
	
	public List<BoardVo> board10() {
		List<BoardVo> list = mapper.board10();
		return list;
	}
	
	public BoardVo view(int sno, String up) {
		BoardVo bVo=null;
		
		//수정 후 상세보기 페이지가 다시 나왔을 때 조회수가 오르지 않게
		if(up != null && up.equals("up")) {
			mapper.hitUp(pVo.getSno());
		}
		
		bVo = mapper.view(sno);
		List<AttVo> attList = mapper.attList(sno);
		bVo.setAttList(attList);
//		List<AttVo> attList = mapper.attList(pVo.getSno());
//		bVo.setAttList(attList);
		
		return bVo;
	}
	
	public boolean delete(BoardVo bVo) {
		boolean b = true;
		
		int replCnt = mapper.replCheck(bVo);
		
		if(replCnt>0) {
			b=false;
			return b;
		}
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(bVo);
		if(cnt<1) {
			b=false;
		}else {
			List<String> attList = mapper.delFileList(bVo.getSno());
			if(attList.size()>0) {
				cnt = mapper.attDeleteAll(bVo.getSno());
				if(cnt>0) {
					if(attList.size()>0) {
						String[] delList = attList.toArray(new String[0]);
						fileDelete(delList);
					}else {
						b=false;
					}
				}
			}
		}
		if(b) manager.commit(status);
		else status.rollbackToSavepoint(savePoint);
		return b;
	}
	
	public boolean updateR(BoardVo bVo, String[] delFiles) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		
		boolean b = true;
		int cnt = boardMapper.update(bVo);
		if(cnt<1) {
			b=false;
		}else if(bVo.getAttList().size()>0) {
			int attCnt = boardMapper.attUpdate(bVo);
			if(attCnt<1) {
				b=false;
			}
		}
		
		if(b) {
			manager.commit(status);
			if(delFiles != null && delFiles.length>0) {
				//첨부 파일 데이터 삭제
				cnt = boardMapper.attDelete(delFiles);
				if(cnt>0) {
					fileDelete(delFiles); //파일 삭제
				}else {
					b=false;
				}
			}
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		return b;
	}
	
	public boolean replR(BoardVo bVo) {
		status = manager.getTransaction(new DefaultTransactionDefinition());
		savePoint = status.createSavepoint();
		
		boolean b = true;
		boardMapper.seqUp(bVo);
		int cnt = boardMapper.replR(bVo);
		if(cnt<1) {
			b=false;
		}else if(bVo.getAttList().size()>0) {
			int attCnt = boardMapper.insertAttList(bVo.getAttList());
			if(attCnt<1) {
				b=false;
			}
		}
		
		if(b) { manager.commit(status); }
		else { status.rollbackToSavepoint(savePoint); }
		return b;
	}

	public PageVo getpVo() { return pVo; }
	
	public void fileDelete(String [] delFiles) {
		for(String f : delFiles) {
			File file = new File(FileUploadController.path + f);
			if(file.exists()) file.delete();
		}
	}

}
