package com.hndfsj.springboot.framework.base;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/24
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseService<T> {
//    protected Mapper<T> baseMapper;
//    protected MySqlMapper<T> mySqlMapper;

    @Resource
    private IBaseService<T> baseMapper;

    /**
     * 根据主键获取对象
     *
     * @param id
     * @return
     */
    public T getById(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件返回一条记录
     *
     * @param t
     * @return
     */
    public T selectOne(T t) {
        return baseMapper.selectOne(t);
    }

    /**
     * 根据复杂条件查找一条记录
     *
     * @param example
     * @return
     */
    public T selectOneByExample(Example example) {
        return baseMapper.selectOneByExample(example);
    }

    /**
     * 查询全部list数据
     *
     * @return
     */
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    /**
     * 查询分页list数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageInfo<T> selectAllPage(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<T> list = baseMapper.selectAll();
        return new PageInfo<T>(list);
    }


    /**
     * 根据复杂条件进行查询
     *
     * @param example
     * @return
     */
    public List<T> selectByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    /**
     * 根据example条件进行分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param example
     * @return
     */
    public PageInfo<T> selectByExamplePage(int pageIndex, int pageSize, Example example) {
        PageHelper.startPage(pageIndex, pageSize);
        List<T> list = baseMapper.selectByExample(example);
        return new PageInfo<T>(list);
    }

    /**
     * 新增数据
     *
     * @param t
     * @return
     */
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    /**
     * 新增数据（set过的不为空的字段）
     *
     * @param t
     * @return
     */
    public int insertSelective(T t) {
        return baseMapper.insertSelective(t);
    }

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    public int insertList(List<T> list) {
        return baseMapper.insertList(list);
    }

    /**
     * 计算数据总数
     *
     * @param t
     * @return
     */
    public int selectCount(T t) {
        return baseMapper.selectCount(t);
    }

    /**
     * 根据条件计算count
     *
     * @param example
     * @return
     */
    public int selectCountByExample(Example example) {
        return baseMapper.selectCountByExample(example);
    }

    /**
     * 根据id判断记录是否存在
     *
     * @param id
     * @return
     */
    public boolean isExistsById(Object id) {
        return baseMapper.existsWithPrimaryKey(id);
    }

    /**
     * 按字段删除
     *
     * @param t
     * @return
     */
    public int delete(T t) {
        return baseMapper.delete(t);
    }

    /**
     * 根据example条件删除
     *
     * @param example
     * @return
     */
    public int deleteByExample(Example example) {
        return baseMapper.deleteByExample(example);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public int deleteById(Object id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
}
