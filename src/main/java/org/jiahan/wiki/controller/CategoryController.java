package org.jiahan.wiki.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.jiahan.wiki.req.CategoryQueryReq;
import org.jiahan.wiki.req.CategorySaveReq;
import org.jiahan.wiki.resp.CategoryQueryResp;
import org.jiahan.wiki.resp.CommonResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public CommonResp all(){

        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();

        List<CategoryQueryResp> list = categoryService.all();

        resp.setContent(list);

        return resp;

    }
    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req){

        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();

        PageResp<CategoryQueryResp> list = categoryService.list(req);

        resp.setContent(list);

        return resp;

    }
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){

        CommonResp resp = new CommonResp<>();

        categoryService.save(req);

        return resp;

    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){

        CommonResp resp = new CommonResp<>();

        categoryService.delete(id);

        LOG.info("ConID="+id);

        return resp;

    }


}
