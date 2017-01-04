package com.zehin.vpaas.beans;

public class Pager {
	private int totalRows; // 总行数
	private int pageSize = 11; // 每页显示的行数
	private int currentPage; // 当前页号
	private int totalPages; // 总页数
	private int startRow; // 当前页在数据库中的起始行
	private int endRow; // 结束行 此为oracle查询需要增加

	public Pager() {
	}

	public Pager(int _totalRows) {
		totalRows = _totalRows;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		currentPage = 1;
		startRow = 0;
		endRow = startRow + pageSize;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
		endRow = startRow + pageSize;
	}

	public void previous() {
		if (currentPage == 1 || currentPage == 0) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
		endRow = startRow + pageSize;
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
		endRow = startRow + pageSize;
	}

	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
		endRow = startRow + pageSize;
	}
	
	public void refresh(int page){
		this.currentPage = page;
	}

}