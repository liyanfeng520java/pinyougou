package com.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }


    /**
     * 分页加上条件查询
     *
     * @param tbBrand
     * @param pageNum  当前页码
     * @param pageSize 当前每页显示记录
     * @return
     */
    @Override
    public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize) {
        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
        //tbBrand不为空的话要加上条件查询的条件
        if (tbBrand != null) {
            //再判断name是否为空
            if (tbBrand.getName() != null && tbBrand.getName().length() > 0) {
                criteria.andNameLike("%" + tbBrand.getName() + "%");
            }
            //判断首字母是否为空
            if (tbBrand.getFirstChar() != null && tbBrand.getFirstChar().length() > 0) {
                criteria.andFirstCharLike("%" + tbBrand.getFirstChar() + "%");
            }
        }
        //开始查询之前执行分页
        PageHelper.startPage(pageNum, pageSize);
        //将查询的结果直接封装到Mybatis - 分页对象中
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 添加品牌
     *
     * @param tbBrand
     */
    @Override
    public void add(TbBrand tbBrand) throws Exception {
        //添加之前先判断是否重复
        TbBrandExample tbBrandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
        criteria.andNameEqualTo(tbBrand.getName());
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(tbBrandExample);
        if (tbBrands != null & tbBrands.size() > 0) {
            //如果重复抛出异常
            throw new Exception();
        } else {
            //如果不重复添加品牌
            tbBrandMapper.insert(tbBrand);
        }
    }

    /**
     * 查询当前品牌的信息
     *
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改品牌信息
     *
     * @param tbBrand
     */
    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }


    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delect(Long[] ids) {
        //先遍历
        for (Long id : ids) {
            //调用单个删除的方法
            delectableOne(id);
        }
    }


    /**
     * 删除单条数据
     *
     * @param id
     */
    public void delectableOne(Long id) {
        tbBrandMapper.deleteByPrimaryKey(id);
    }


}
