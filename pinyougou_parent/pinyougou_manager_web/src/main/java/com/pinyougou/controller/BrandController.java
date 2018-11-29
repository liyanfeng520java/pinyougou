package com.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    //从远程获取接口的服务实现
    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }


    /**
     * 实现分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    /*@RequestMapping("findPage")
    public PageResult findPage(int pageNum,int pageSize){
        return brandService.findPage(tbBrand, pageNum,pageSize);
    }*/

    /**
     * 添加品牌
     * @param tbBrand
     * @return
     */
    @RequestMapping("add")
    //前台post请求提交的如果是一个对象需要添加@RequestBody注解
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");

        }
    }

    /**
     * 查询当前品牌的信息
     * @param id
     * @return
     */
    @RequestMapping("findOne")
    public TbBrand findOne(Long id){
        TbBrand tbBrand=brandService.findOne(id);
        return tbBrand;
    }


    /**
     * 修改品牌信息
     * @param tbBrand
     * @return
     */
    @RequestMapping("update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("delect")
    public Result delect(Long[] ids){
        try {
            brandService.delect(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    /**
     * 条件查询
     * @param tbBrand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("query")
    public PageResult query(@RequestBody TbBrand tbBrand, int pageNum,int pageSize){
        return brandService.findPage(tbBrand,pageNum,pageSize);
    }

}
