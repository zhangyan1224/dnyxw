package com.db.common.vo;

import java.util.List;

/**
 * VO：通过此对象封装角色以及角色对应的菜单id
 * @author 
 */
public class SysRoleMenuVo {
	/**角色名称*/
	private String name;
	/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
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
	@Override
	public String toString() {
		return "SysRoleMenuVo [name=" + name + ", note=" + note + ", menuIds=" + menuIds + "]";
	}
	
}
