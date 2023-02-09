package com.daxiong.mall.controller;

import com.daxiong.mall.Service.CategoryService;
import com.daxiong.mall.Service.UserService;
import com.daxiong.mall.common.ApiRestResponse;
import com.daxiong.mall.common.Constant;
import com.daxiong.mall.exception.MallExceptionEnum;
import com.daxiong.mall.model.pojo.Category;
import com.daxiong.mall.model.pojo.User;
import com.daxiong.mall.model.request.AddCategoryReq;
import com.daxiong.mall.model.request.UpdateCategoryReq;
import com.daxiong.mall.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：     目录Controller
 */
@RestController
public class CategoryController {

    @Resource
    UserService userService;

    @Resource
    CategoryService categoryService;

    /**
     * 后台添加目录
     */
    //@ApiOperation("后台添加目录")
    @PostMapping("admin/category/add")
    public ApiRestResponse addCategory(HttpSession session,
                                       @Valid @RequestBody AddCategoryReq addCategoryReq) {
       User currentUser = (User) session.getAttribute(Constant.CURRENT_MALL_USER);
       if (currentUser == null) {
           return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
       }
       //校验是否是管理员
       boolean adminRole = userService.checkAdminRole(currentUser);
       if (adminRole) {
           //是管理员，执行操作
           categoryService.add(addCategoryReq);
           return ApiRestResponse.success();
       } else {
           return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
       }
    }

    //@ApiOperation("后台更新目录")
    @PostMapping("admin/category/update")
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq,
            HttpSession session) {

       User currentUser = (User) session.getAttribute(Constant.CURRENT_MALL_USER);
       if (currentUser == null) {
           return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
       }
       //校验是否是管理员
       boolean adminRole = userService.checkAdminRole(currentUser);
       if (adminRole) {
           //是管理员，执行操作
           Category category = new Category();
           BeanUtils.copyProperties(updateCategoryReq, category);
           categoryService.update(category);
           return ApiRestResponse.success();
       } else {
           return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
       }
    }

    //@ApiOperation("后台删除目录")
    @PostMapping("admin/category/delete")
    public ApiRestResponse deleteCategory(@RequestParam Integer id) {
      

       categoryService.delete(id);
       return ApiRestResponse.success();
    }

    //@ApiOperation("后台目录列表")
    @GetMapping("admin/category/list")
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    //@ApiOperation("前台目录列表")
    @GetMapping("category/list")
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }
}
