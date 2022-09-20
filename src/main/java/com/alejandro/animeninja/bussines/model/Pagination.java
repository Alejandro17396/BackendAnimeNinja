package com.alejandro.animeninja.bussines.model;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {

	private List<T> list;
	private int page;
	private int pageSize;
	private int numPages;

	public Pagination() {
		super();
		this.list = new ArrayList<T>();
		this.pageSize = 5;
	}

	public Pagination(List<T> list, int page, int pageSize) {
		super();
		if (list == null) {
			this.list = new ArrayList<T>();
		} else {
			this.list = list;
		}

		if (pageSize < 1) {
			this.pageSize = 5;
		} else if (pageSize > 100) {
			this.pageSize = 50;
		} else {
			this.pageSize = pageSize;
		}

		this.page = page;

		this.numPages = calculateNumPages();

	}

	private int calculateNumPages() {
		if (list.size() < 1) {
			return 0;
		}
		int rest = list.size() % pageSize;
		int num = list.size() / pageSize;

		return (rest != 0) ? num + 1 : num;
	}

	public List<T> getList() {
		return list;
	}

	public void addElement(T formation) {
		if (list.add(formation)) {
			calculateNumPages();
		}
	}

	public void removeElement(T formation) {
		if (list.remove(formation)) {
			calculateNumPages();
		}
	}

	public void setList(List<T> list) {
		this.list = list;
		calculateNumPages();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > list.size() || pageSize < 1) {
			this.pageSize = 5;
		} else if (pageSize > 100) {
			this.pageSize = 50;
		} else {
			this.pageSize = pageSize;
		}
		calculateNumPages();
		this.pageSize = pageSize;
	}

	public List<T> getPage(int page) {
		if (page < 1) {
			return getSublist(1);
		}
		if (page >= numPages) {
			return lastPage();
		}
		return getSublist(page);
	}

	private List<T> getSublist(int i) {
		if (list.size() < 1) {
			return list;
		}

		if (i * pageSize > list.size() && list.size() != 0) {
			return list.subList(0, list.size());
		}
		return list.subList((i - 1) * pageSize, i * pageSize);
	}

	private List<T> lastPage() {
		if (list.size() < 1) {
			return list;
		}

		int rest = list.size() % pageSize;
		int num = list.size() / pageSize;
		if (rest == 0) {
			int end = list.size();
			return list.subList(end - pageSize, end);
		} else {
			return list.subList(num * pageSize, num * pageSize + rest);
		}
	}

	public List<T> getPagedList() {
		return getPage(page);
	}

}
