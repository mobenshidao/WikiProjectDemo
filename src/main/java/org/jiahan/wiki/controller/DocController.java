package org.jiahan.wiki.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.jiahan.wiki.req.DocQueryReq;
import org.jiahan.wiki.req.DocSaveReq;
import org.jiahan.wiki.resp.DocQueryResp;
import org.jiahan.wiki.resp.CommonResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);
    @Resource
    private DocService docService;

    @GetMapping("/all")
    public CommonResp all(){

        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();

        List<DocQueryResp> list = docService.all();

        resp.setContent(list);

        return resp;

    }
    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req){

        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();

        PageResp<DocQueryResp> list = docService.list(req);

        resp.setContent(list);

        return resp;

    }
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req){

        CommonResp resp = new CommonResp<>();

        docService.save(req);

        return resp;

    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){

        CommonResp resp = new CommonResp<>();

        docService.delete(id);

        LOG.info("ConID="+id);

        return resp;

    }


}
