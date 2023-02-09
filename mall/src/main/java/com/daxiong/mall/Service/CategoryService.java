package com.daxiong.mall.Service;

import com.daxiong.mall.model.pojo.Category;
import com.daxiong.mall.model.request.AddCategoryReq;
import com.daxiong.mall.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 描述：     分类目录Service
 */
public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
