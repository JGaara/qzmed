package com.zehin.vpaas.generic;

import java.util.List;

/**
 * 所有自定义Dao的顶级接口, 封装常用的增删查改操作,
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 *
 * @ClassName: BaseDao
 * @Description: (这里用一句话描述这个类的作用)
 * @author kunka kong0609@163.com
 * @date 2015年12月21日 上午10:24:48
 * @param <Model>
 * @param <PK>
 *
 */
public interface BaseDao<Model, PK> {

	/**
	 * @Title: insertSelective 
	 * @Description: 非空插入
	 * @param model
	 * @return int
	 * @throws
	 */
	int insertSelective(Model model);

	/**
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: 非空更新 
	 * @param model
	 * @return int
	 * @throws
	 */
	int updateByPrimaryKeySelective(Model model);

	/**
	 * @Title: deleteByPrimaryKey 
	 * @Description: 根据主键删除
	 * @param id
	 * @return int
	 * @throws
	 */
	int deleteByPrimaryKey(PK id);

	/**
	 * @Title: selectByPrimaryKey 
	 * @Description: 根据主键查询
	 * @param id
	 * @return Model
	 * @throws
	 */
	Model selectByPrimaryKey(PK id);

	/**
	 * @Title: deleteBT 
	 * @Description: 批量删除
	 * @param ids
	 * @return int
	 * @throws
	 */
	int deleteBT(String... ids);

	/**
	 * @Title: findByPage 
	 * @Description: 不分页查询
	 * @param model
	 * @return List<Model>
	 * @throws
	 */
	List<Model> findByPage(Model model);

}
