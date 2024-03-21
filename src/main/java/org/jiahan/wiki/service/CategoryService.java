package org.jiahan.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Category;
import org.jiahan.wiki.domain.CategoryExample;
import org.jiahan.wiki.mapper.CategoryMapper;
import org.jiahan.wiki.req.CategoryQueryReq;
import org.jiahan.wiki.req.CategorySaveReq;
import org.jiahan.wiki.resp.CategoryQueryResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.utils.CopyUtil;
import org.jiahan.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    public SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("Rows: {}",pageInfo.getTotal());
        LOG.info("Pages: {}",pageInfo.getPages());

//        List<CategoryResp> respList = new ArrayList<>();

//         for (Category category : categoryList) {
//             // CategoryResp categoryResp = new CategoryResp();
//             // BeanUtils.copyProperties(category, categoryResp);
//             CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
//
//             respList.add(categoryResp);
//         }

        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    public void save(CategorySaveReq req){

        Category category =CopyUtil.copy(req,Category.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }

    }

    public void delete(Long id){

        categoryMapper.deleteByPrimaryKey(id);
        LOG.info("SerID="+id);

    }

}
