package org.jiahan.wiki.service;

import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Ebook;
import org.jiahan.wiki.domain.EbookExample;
import org.jiahan.wiki.mapper.EbookMapper;
import org.jiahan.wiki.req.EbookReq;
import org.jiahan.wiki.resp.EbookResp;
import org.jiahan.wiki.utils.CopyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){

        EbookExample ebookExample = new EbookExample();

        EbookExample.Criteria criteria = ebookExample.createCriteria();

        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();

//         for (Ebook ebook : ebookList) {
//             // EbookResp ebookResp = new EbookResp();
//             // BeanUtils.copyProperties(ebook, ebookResp);
//             EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//
//             respList.add(ebookResp);
//         }

        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        return list;

    }

}
