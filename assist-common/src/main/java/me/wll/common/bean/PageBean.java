package me.wll.common.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PageBean 分页数据对象")
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("总条数")
	private Long totalcount;

	@ApiModelProperty("总页数")
	private Integer totalPages;

	@ApiModelProperty("页码")
	private Integer pageNumber;

	@ApiModelProperty("数据")
	private List<T> retlist;

	public PageBean(List<T> data, Pageable pageable, Long total) {
		this.retlist = data;
		this.totalcount = total;
		int size = pageable.isPaged() ? pageable.getPageSize() : data.size();
		this.totalPages = size == 0 ? 1 : (int) Math.ceil((double) total / (double) size);
		this.pageNumber = (pageable.isPaged() ? pageable.getPageNumber() : 0) + 1;
	}

	public Long getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getRetlist() {
		return retlist;
	}

	public void setRetlist(List<T> retlist) {
		this.retlist = retlist;
	}

}
