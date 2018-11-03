package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import com.pinyougou.service.impl.BaseServiceImpl;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service
public class SpecificationServiceImpl extends BaseServiceImpl<TbSpecification> implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult search(TbSpecification specification, Integer page, Integer rows) {

        //设置分页
        PageHelper.startPage(page,rows);
        //设置查询条件
        Example example = new Example(TbSpecification.class);
        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(specification.getSpecName())){
            criteria.andLike("specName","%"+specification.getSpecName()+"%");
        }

        List<TbSpecification> list = specificationMapper.selectByExample(example);
        PageInfo<TbSpecification> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void add(Specification specification) {
        specificationMapper.insertSelective(specification.getSpecification());

        if(null != specification.getSpecificationOptionList() && specification.getSpecificationOptionList().size()>0){

            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()){
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insertSelective(specificationOption);
            }
        }

    }

    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();
        //查询并设置规格
        specification.setSpecification(specificationMapper.selectByPrimaryKey(id));

        //查询并设置规格选项列表
        TbSpecificationOption specificationOption = new TbSpecificationOption();
        specificationOption.setSpecId(id);

        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.select(specificationOption);
        specification.setSpecificationOptionList(specificationOptionList);
        return specification;
    }

    @Override
    public void update(Specification specification) {
        //更新规格
        specificationMapper.updateByPrimaryKeySelective(specification.getSpecification());

        //删除原有的规格选项
        TbSpecificationOption specificationOption = new TbSpecificationOption();
        specificationOption.setSpecId(specification.getSpecification().getId());
        specificationOptionMapper.delete(specificationOption);

        if(null != specification.getSpecificationOptionList() && specification.getSpecificationOptionList().size()>0){

            for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()){
                tbSpecificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insertSelective(tbSpecificationOption);
            }
        }
    }

    @Override
    public void delete(Long[] ids) {
        //删除规格
        deleteByIds(ids);

        //批量删除规格选项
        Example example = new Example(TbSpecificationOption.class);
        example.createCriteria().andIn("specId", Arrays.asList(ids));
        specificationOptionMapper.deleteByExample(example);
    }
}
