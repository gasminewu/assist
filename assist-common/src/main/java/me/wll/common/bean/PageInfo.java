package me.wll.common.bean;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

public class PageInfo {
	private int curpage;
	private int percount;
	private long totalcount;
	private int totalpage;
	private List<?> retlist;

	public int getCurpage() {
		return this.curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage > 0 ? curpage : 1;
	}

	public int getPercount() {
		return this.percount;
	}

	public void setPercount(int percount) {
		this.percount = percount > 0 ? percount : 10;
	}

	public long getTotalcount() {
		return this.totalcount;
	}

	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount > -1L ? totalcount : 0L;
	}

	public int getTotalpage() {
		return this.getPercount() == 0
				? 0
				: (this.totalpage > 0
						? this.totalpage
						: (int) ((this.getTotalcount() + (long) this.getPercount() - 1L) / (long) this.getPercount()));
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public List<?> getRetlist() {
		return this.retlist;
	}

	public void setRetlist(List<?> retlist) {
		this.retlist = retlist;
	}
	
	public PageInfo() {
		super();
	}

	public PageInfo(List<?> data, Pageable pageable, Long total) {
		this.retlist = data;
		this.totalcount = total;
		int size = pageable.isPaged() ? pageable.getPageSize() : data.size();
		this.totalpage = size == 0 ? 1 : (int) Math.ceil((double) total / (double) size);
	}
}
