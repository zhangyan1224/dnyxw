package com.db.sys.entity;


import java.util.Date;

/**
 @author ifsy
 @create 2019年1月24日 下午4:04:42
*/
public class SysGoods extends BaseEntity {

	private Integer id;
	//游戏名字
	private String youxi_name;
	//游戏账号
	private String youxi_zhanghao;
	//游戏密码
	private String youxi_mima;
	//游戏区服
	private String youxi_qufu;
	//游戏等级
	private Integer youxi_dengji;
	//商品数量
	private Integer shangpin_count;
	//商品价格
	private Integer shangpin_jiage;
	//上架人
	private String shangjia_name;
	//上架时间
	private Date shangjia_time;
	//购买人
	private String goumai_name;
	//购买时间
	private Date goumai_time;
	//商品状态
	private String shangpin_zhuangtai;
	//商品类型
	private String shangpin_type;
	//买家状态
	private String maijia_zhuangtai;
	//商家状态
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

	public Integer getYouxi_dengji() {
		return youxi_dengji;
	}

	public void setYouxi_dengji(Integer youxi_dengji) {
		this.youxi_dengji = youxi_dengji;
	}

	public Integer getShangpin_count() {
		return shangpin_count;
	}

	public void setShangpin_count(Integer shangpin_count) {
		this.shangpin_count = shangpin_count;
	}

	public Integer getShangpin_jiage() {
		return shangpin_jiage;
	}

	public void setShangpin_jiage(Integer shangpin_jiage) {
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
		return "SysGoods{" +
				"id=" + id +
				", youxi_name='" + youxi_name + '\'' +
				", youxi_zhanghao='" + youxi_zhanghao + '\'' +
				", youxi_mima='" + youxi_mima + '\'' +
				", youxi_qufu='" + youxi_qufu + '\'' +
				", youxi_dengji='" + youxi_dengji + '\'' +
				", shangpin_count=" + shangpin_count +
				", shangpin_jiage=" + shangpin_jiage +
				", shangjia_name='" + shangjia_name + '\'' +
				", shangjia_time=" + shangjia_time +
				", goumai_name='" + goumai_name + '\'' +
				", goumai_time=" + goumai_time +
				", shangpin_zhuangtai='" + shangpin_zhuangtai + '\'' +
				", shangpin_type=" + shangpin_type +
				", maijia_zhuangtai='" + maijia_zhuangtai + '\'' +
				", shangjia_zhuangtai='" + shangjia_zhuangtai + '\'' +
				'}';
	}
}
