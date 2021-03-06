package com.db.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysShangpin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6789525731114047045L;
	private Integer id;
	//youxi_name
	private String youxi_name;
	//youxi_zhanghao
	private String youxi_zhanghao;
	//youxi_mima
	private String youxi_mima;
	//youxi_qufu
	private String youxi_qufu;
	//youxi_dengji
	private int youxi_dengji;
	//shangpin_count
	private int shangpin_count;
	//shangpin_jiage
	private int shangpin_jiage;
	//shangjia_name
	private String shangjia_name;
	//shangjia_time
	private Date shangjia_time;
	//goumai_name
	private String goumai_name;
	//goumai_time
	private Date goumai_time;
	//shangpin_zhuangtai
	private String shangpin_zhuangtai;
	//shangpin_type
	private String shangpin_type;
	//maijia_zhuangtai
	private String maijia_zhuangtai;
	//shangjia_zhuangtai
	private String shangjia_zhuangtai;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYouxi_name() {
		return youxi_name;
	}
	public void setYouxi_name(String youxi_name) {
		this.youxi_name = youxi_name;
	}
	public String getYouxi_zhanghao() {
		return youxi_zhanghao;
	}
	public void setYouxi_zhanghao(String youxi_zhanghao) {
		this.youxi_zhanghao = youxi_zhanghao;
	}
	public String getYouxi_mima() {
		return youxi_mima;
	}
	public void setYouxi_mima(String youxi_mima) {
		this.youxi_mima = youxi_mima;
	}
	public String getYouxi_qufu() {
		return youxi_qufu;
	}
	public void setYouxi_qufu(String youxi_qufu) {
		this.youxi_qufu = youxi_qufu;
	}
	public int getYouxi_dengji() {
		return youxi_dengji;
	}
	public void setYouxi_dengji(int youxi_dengji) {
		this.youxi_dengji = youxi_dengji;
	}
	public int getShangpin_count() {
		return shangpin_count;
	}
	public void setShangpin_count(int shangpin_count) {
		this.shangpin_count = shangpin_count;
	}
	public int getShangpin_jiage() {
		return shangpin_jiage;
	}
	public void setShangpin_jiage(int shangpin_jiage) {
		this.shangpin_jiage = shangpin_jiage;
	}
	public String getShangjia_name() {
		return shangjia_name;
	}
	public void setShangjia_name(String shangjia_name) {
		this.shangjia_name = shangjia_name;
	}
	public Date getShangjia_time() {
		return shangjia_time;
	}
	public void setShangjia_time(Date shangjia_time) {
		this.shangjia_time = shangjia_time;
	}
	public String getGoumai_name() {
		return goumai_name;
	}
	public void setGoumai_name(String goumai_name) {
		this.goumai_name = goumai_name;
	}
	public Date getGoumai_time() {
		return goumai_time;
	}
	public void setGoumai_time(Date goumai_time) {
		this.goumai_time = goumai_time;
	}
	public String getShangpin_zhuangtai() {
		return shangpin_zhuangtai;
	}
	public void setShangpin_zhuangtai(String shangpin_zhuangtai) {
		this.shangpin_zhuangtai = shangpin_zhuangtai;
	}
	public String getShangpin_type() {
		return shangpin_type;
	}
	public void setShangpin_type(String shangpin_type) {
		this.shangpin_type = shangpin_type;
	}
	public String getMaijia_zhuangtai() {
		return maijia_zhuangtai;
	}
	public void setMaijia_zhuangtai(String maijia_zhuangtai) {
		this.maijia_zhuangtai = maijia_zhuangtai;
	}
	public String getShangjia_zhuangtai() {
		return shangjia_zhuangtai;
	}
	public void setShangjia_zhuangtai(String shangjia_zhuangtai) {
		this.shangjia_zhuangtai = shangjia_zhuangtai;
	}
	@Override
	public String toString() {
		return "SysShangpin [id=" + id + ", youxi_name=" + youxi_name + ", youxi_zhanghao=" + youxi_zhanghao
				+ ", youxi_mima=" + youxi_mima + ", youxi_qufu=" + youxi_qufu + ", youxi_dengji=" + youxi_dengji
				+ ", shangpin_count=" + shangpin_count + ", shangpin_jiage=" + shangpin_jiage + ", shangjia_name="
				+ shangjia_name + ", shangjia_time=" + shangjia_time + ", goumai_name=" + goumai_name + ", goumai_time="
				+ goumai_time + ", shangpin_zhuangtai=" + shangpin_zhuangtai + ", shangpin_type=" + shangpin_type
				+ ", maijia_zhuangtai=" + maijia_zhuangtai + ", shangjia_zhuangtai=" + shangjia_zhuangtai + "]";
	}
	
}
