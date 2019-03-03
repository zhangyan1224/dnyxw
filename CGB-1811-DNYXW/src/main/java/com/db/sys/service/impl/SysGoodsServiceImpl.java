package com.db.sys.service.impl;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysCartDao;
import com.db.sys.dao.SysGoodsDao;
import com.db.sys.entity.SysGoods;
import com.db.sys.service.SysGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 @author ifsy
 @create 2019年1月31日 下午2:55:24
*/
@Transactional(rollbackFor = Throwable.class,timeout = 30,isolation = Isolation.READ_COMMITTED)
@Service
public class SysGoodsServiceImpl implements SysGoodsService {
	@Autowired//DI(spring 采用DI方式自動为屬性賦值)
	@Qualifier("sysGoodsDao")//可以基于此注解指定这个接口具体实现类
	private SysGoodsDao sysGoodsDao;

	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		return null;
	}

	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		return 0;
	}

	@Override
	public PageObject<SysGoods> findPageObjects(String youxi_name,String youxi_qufu,String shangpin_type,String shangpin_zhuangtai,String youxi_zhanghao,Integer pageCurrent,Integer priceOrder) {
		//1.验证参数的有效性
		if(pageCurrent==null ||pageCurrent<1) throw new IllegalArgumentException("页码值不正确");
		//2.基于用户名查询总记录数并进行相关判定
		System.out.println("Impl:findPageObjects:getRowCount:priceOrderBy"+youxi_name+":"+shangpin_type+":"+shangpin_zhuangtai+priceOrder);
		int rowCount = sysGoodsDao.getRowCount(youxi_name,youxi_qufu,shangpin_type,shangpin_zhuangtai,youxi_zhanghao);
		System.out.println("rowCount"+rowCount);
		if (rowCount==0) throw new ServiceException("没有找到对应记录");
		//3.基于用户名以及当前页码值查询当前的记录
		List<SysGoods> records;
		int pageSize=20;
		int startIndex=(pageCurrent-1)*pageSize;
		if (priceOrder==2){
			records =sysGoodsDao.findPageObjectsOrderByjiageAsc(youxi_name,youxi_qufu,shangpin_type,shangpin_zhuangtai,youxi_zhanghao,startIndex,pageSize,priceOrder);
		}else if(priceOrder==1){
			records =sysGoodsDao.findPageObjectsOrderByjiageDesc(youxi_name,youxi_qufu,shangpin_type,shangpin_zhuangtai,youxi_zhanghao,startIndex,pageSize,priceOrder);
		} else{
			records = sysGoodsDao.findPageObjects(youxi_name,youxi_qufu,shangpin_type,shangpin_zhuangtai,youxi_zhanghao,startIndex,pageSize);
		}
		System.out.println("Impl层："+youxi_name+":"+youxi_qufu+":"+shangpin_type+":"+shangpin_zhuangtai+":"+startIndex+":"+pageSize);
		System.out.println("Impl层：records:"+records);
		//4.封装两次结果并返回
		PageObject<SysGoods> po = new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount-1)/pageSize+1);
		return po;
	}

	@Override
	public int saveObject(Integer user_id, Integer shangpin_id) {
		int row = sysGoodsDao.insertObject(user_id, shangpin_id);
		return row;
	}

	@Override
	public List<SysGoods> findGamenames() {
		List<SysGoods> gamenames = sysGoodsDao.findGamenames();
		return gamenames;
	}

	@Override
	public List<SysGoods> findGamequfus(String youxi_name) {
		List<SysGoods> gamequfus = sysGoodsDao.findGamequfus(youxi_name);
		return gamequfus;
	}
}
