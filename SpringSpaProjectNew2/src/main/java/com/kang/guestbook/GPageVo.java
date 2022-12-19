package com.kang.guestbook;

public class GPageVo {
	int totSize;
	int totPage;
	int startPage;
	int endPage;
	int blockSize = 5;
	int nowPage   = 1;
	int listSize  = 4;
	int startNo;
	int endNo;
	int sno;
	String findStr;
	
	public void pageCompute() {
		totPage = (int)Math.ceil(totSize/(double)listSize);
		endNo = nowPage * listSize;
		startNo = endNo - listSize;
		if(endNo > totSize) endNo = totSize;
		
		endPage = (int)Math.ceil(nowPage/(double)blockSize) * blockSize;
		startPage = endPage - blockSize + 1;
		if(endPage > totPage) endPage = totPage;
	}
	
	public int getTotSize() { return totSize; }
	public void setTotSize(int totSize) {
		this.totSize = totSize;
		pageCompute();
	}
	public int getTotPage() { return totPage; }
	public void setTotPage(int totPage) { this.totPage = totPage; }
	public int getStartPage() { return startPage; }
	public void setStartPage(int startPage) { this.startPage = startPage; }
	public int getEndPage() { return endPage; }
	public void setEndPage(int endPage) { this.endPage = endPage; }
	public int getBlockSize() { return blockSize; }
	public void setBlockSize(int blockSize) { this.blockSize = blockSize; }
	public int getNowPage() { return nowPage; }
	public void setNowPage(int nowPage) { this.nowPage = nowPage; }
	public int getListSize() { return listSize; }
	public void setListSize(int listSize) { this.listSize = listSize; }
	public int getStartNo() { return startNo; }
	public void setStartNo(int startNo) { this.startNo = startNo; }
	public int getEndNo() { return endNo; }
	public void setEndNo(int endNo) { this.endNo = endNo; }
	public String getFindStr() { return findStr; }
	public void setFindStr(String findStr) { this.findStr = findStr; }
	public int getSno() { return sno; }
	public void setSno(int sno) { this.sno = sno; }
}
