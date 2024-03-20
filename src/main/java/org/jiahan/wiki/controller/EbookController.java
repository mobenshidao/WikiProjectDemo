package org.jiahan.wiki.controller;

import jakarta.annotation.Resource;
import org.jiahan.wiki.req.EbookQueryReq;
import org.jiahan.wiki.req.EbookSaveReq;
import org.jiahan.wiki.resp.CommonResp;
import org.jiahan.wiki.resp.EbookResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.service.EbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req){

        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();

        PageResp<EbookResp> list = ebookService.list(req);

        resp.setContent(list);

        return resp;

    }
    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req){

        CommonResp resp = new CommonResp<>();

        ebookService.save(req);

        return resp;

    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){

        CommonResp resp = new CommonResp<>();

        ebookService.delete(id);

        LOG.info("ConID="+id);

        return resp;

    }


}
