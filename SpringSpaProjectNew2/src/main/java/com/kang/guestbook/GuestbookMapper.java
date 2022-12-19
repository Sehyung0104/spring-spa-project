package com.kang.guestbook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GuestbookMapper {
	public int totSize(GPageVo pVo);
	public List<GuestbookVo> list(GPageVo pVo);
	public int insert(GuestbookVo gVo);
	public int delete(GuestbookVo gVo);
	public int update(GuestbookVo gVo);
	public List<GuestbookVo> guestbook10(GPageVo pVo);
}
