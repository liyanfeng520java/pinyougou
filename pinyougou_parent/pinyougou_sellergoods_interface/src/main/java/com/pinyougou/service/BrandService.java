package com.pinyougou.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;


import java.util.List;

public interface BrandService {

    public List<TbBrand> findAll();

    /**
     * 分页
     *
     * @param tbBrand
     * @param pageNum  当前页码
     * @param pageSize 当前每页显示记录
     * @return
     */
    public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize);


    /**
     * 添加品牌
     * @param tbBrand
     */
    void add(TbBrand tbBrand) throws Exception;


    /**
     * 修改品牌信息
     * @param tbBrand
     */
    void update(TbBrand tbBrand);


    /**
     * 查询当前品牌的信息
     * @param id
     * @return
     */
    TbBrand findOne(Long id);

    void delect(Long[] ids);
}
