package org.jiahan.wiki.controller;

import jakarta.annotation.Resource;
import org.jiahan.wiki.req.EbookReq;
import org.jiahan.wiki.resp.CommonResp;
import org.jiahan.wiki.resp.EbookResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req){

        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();

        PageResp<EbookResp> list = ebookService.list(req);

        resp.setContent(list);

        return resp;

    }


}
