package com.zehin.vpaas.generic;

import java.util.List;

/**
 * 所有自定义Service的顶级接口,封装常用的增删查改操作
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型 
 *
 * @ClassName: BaseService
 * @Description: (这里用一句话描述这个类的作用)
 * @author kunka kong0609@163.com
 * @date 2015年12月21日 上午10:59:39
 * @param <Model>
 * @param <PK>
 *
 */
public interface BaseService<Model, PK> {

    /**
     * 插入对象
     *
     * @param model 对象
     */
    int insert(Model model);

    /**
     * 更新对象
     *
     * @param model 对象
     */
    int update(Model model);

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int delete(PK id);

    
    /**
     * 批量删除对象
     *
     * @param id 主键
     */
    int deleteBT(String... id);

    
    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return model 对象
     */
    Model selectById(PK id);

    
    /**
    * @Title: findByPage
    * @Description: 全部查询
    * @param @param model
    * @param @return 设定文件
    * @return List<Model> 返回类型
    * @throws
     */
    List<Model> findAll(Model model);
}
