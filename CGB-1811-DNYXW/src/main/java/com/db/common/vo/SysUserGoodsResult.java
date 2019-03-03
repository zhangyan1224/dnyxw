package com.db.common.vo;

import com.db.sys.entity.BaseEntity;

/**
 @author ifsy
 @create 2019年1月31日 下午2:33:56
*/
public class SysUserGoodsResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1250720064965774099L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "SysUserGoodsResult [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", email=" + email + ", mobile=" + mobile + ", valid=" + valid + ", getId()=" + getId()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getSalt()=" + getSalt()
				+ ", getEmail()=" + getEmail() + ", getMobile()=" + getMobile() + ", getValid()=" + getValid()
				+ ", getCreatedTime()=" + getCreatedTime() + ", getModifiedTime()=" + getModifiedTime()
				+ ", getCreatedUser()=" + getCreatedUser() + ", getModifiedUser()=" + getModifiedUser()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}
