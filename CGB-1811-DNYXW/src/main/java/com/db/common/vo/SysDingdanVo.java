package com.db.common.vo;

import java.util.List;

/**
 @author ifsy
 @create 2019年1月31日 上午9:32:12
 VO：通过此对象封装user以及user对应的shangpin_id
*/
public class SysDingdanVo {
	/** 角色名称 */
	private String name;
	/** 角色备注 */
	private String note;
	/** 角色对应菜单id */
	private List<Integer> menuIds;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
}
