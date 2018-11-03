package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;

public interface BrandService extends BaseService<TbBrand>{

    /**
     * 根据条件查询
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    public PageResult search(TbBrand brand,Integer page,Integer rows);

}
