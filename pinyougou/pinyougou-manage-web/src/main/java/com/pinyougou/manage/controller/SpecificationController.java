package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Result;
import com.pinyougou.vo.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("specification")
@RestController
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }

    /**
     * 搜索
     * @param specification
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbSpecification specification,Integer page,Integer rows){
        return specificationService.search(specification,page,rows);
    }

    /**
     * 添加
     * @param specification
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Specification specification){
        try {
            specificationService.add(specification);
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("添加失败");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/findOne")
    public Specification findOne(Long id){
        return specificationService.findOne(id);
    }

    /**
     * 修改
     * @param specification
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Specification specification){
        try {
            specificationService.update(specification);
            return Result.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("修改失败");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long[] ids){
        try {
            specificationService.delete(ids);
            return Result.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("删除失败");
    }

}
