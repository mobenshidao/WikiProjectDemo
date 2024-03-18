package org.jiahan.wiki.controller;

import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Ebook;
import org.jiahan.wiki.resp.CommonResp;
import org.jiahan.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(){

        CommonResp<List<Ebook>> resp = new CommonResp<>();

        List<Ebook> list = ebookService.list();

        resp.setContent(list);

        return resp;

    }


}
