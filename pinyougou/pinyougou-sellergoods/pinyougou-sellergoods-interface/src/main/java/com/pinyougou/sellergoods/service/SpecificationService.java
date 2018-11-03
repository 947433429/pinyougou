package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Specification;

public interface SpecificationService extends BaseService<TbSpecification>{

    public PageResult search(TbSpecification specification, Integer page, Integer rows);

    public void add(Specification specification);

    public Specification findOne(Long id);

    public void update(Specification specification);

    public void delete(Long[] ids);

}



