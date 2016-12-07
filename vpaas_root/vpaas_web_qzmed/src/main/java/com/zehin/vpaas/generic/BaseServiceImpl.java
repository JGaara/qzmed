package com.zehin.vpaas.generic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @ClassName: BaseServiceImpl
 * @Description: (BaseService的实现类, 其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作, Model
 *               : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型 )
 * @date 2015年12月21日 上午10:34:16
 * @param <Model>
 * @param <PK>
 * 
 */
public abstract class BaseServiceImpl<T extends BaseDao<Model, PK>, Model, PK> implements BaseService<Model, PK> {

	/**
	 * 需要设置为protected
	 */
	@Autowired
	protected T mapper;

	/**
	 * 
	 *
	 * @Title: getDao @Description: (定义成抽象方法,由子类实现,完成dao的注入) @param @return
	 *         设定文件 @return BaseDao<Model,PK> 返回类型 @throws
	 *
	 */
	public BaseDao<Model, PK> getDao() {
		return mapper;
	}

	/**
	 * 插入对象
	 */
	public int insert(Model model) {
		return getDao().insertSelective(model);
	}

	/**
	 * 更新对象
	 */
	public int update(Model model) {
		return getDao().updateByPrimaryKeySelective(model);
	}

	/**
	 * 通过主键, 删除对象
	 */
	public int delete(PK id) {
		return getDao().deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除 为了提高性能不采用循环删除
	 */
	public int deleteBT(String... id) {
		return getDao().deleteBT(id);
	}

	/**
	 * 通过主键, 查询对象
	 */
	public Model selectById(PK id) {
		return getDao().selectByPrimaryKey(id);
	}

	/**
	 * 全部查询
	 */
	public List<Model> findAll(Model model) {
		List<Model> voList = getDao().findByPage(model);
		return voList;
	}
}
