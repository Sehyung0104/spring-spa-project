package com.kang.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Transactional
public class GuestbookDao {
	
	GPageVo pVo;
	
	@Autowired
	GuestbookMapper mapper;
	
	@Autowired
	PlatformTransactionManager manager;
	
	TransactionStatus status;
	
	public int getTotSize(GPageVo pVo) {
		int totSize = 0;
		totSize = mapper.totSize(pVo);
		
		return totSize;
	}
	
	public List<GuestbookVo> select(GPageVo pVo) {
		List<GuestbookVo> list = null;
		int totSize = getTotSize(pVo);
		pVo.setTotSize(totSize); //setTotSize 메소드에서 pageCompute메소드 실행.
		this.pVo = pVo;
		list = mapper.list(pVo);
		
		return list;
	}
	
	public boolean insert(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.insert(gVo);
		if(cnt > 0) {
			b = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public boolean delete(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(gVo);
		if(cnt > 0) {
			b = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public boolean update(GuestbookVo gVo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(gVo);
		if(cnt > 0) {
			b = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public List<GuestbookVo> guestbook10() {
		List<GuestbookVo> list = mapper.guestbook10(pVo);
		return list;
	}

	public GPageVo getgVo() { return pVo; }
	
}
