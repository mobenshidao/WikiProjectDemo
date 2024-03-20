package org.jiahan.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Ebook;
import org.jiahan.wiki.domain.EbookExample;
import org.jiahan.wiki.mapper.EbookMapper;
import org.jiahan.wiki.req.EbookQueryReq;
import org.jiahan.wiki.req.EbookSaveReq;
import org.jiahan.wiki.resp.EbookResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){

            criteria.andNameLike("%" + req.getName() + "%");

        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("Rows: {}",pageInfo.getTotal());
        LOG.info("Pages: {}",pageInfo.getPages());

//        List<EbookResp> respList = new ArrayList<>();

//         for (Ebook ebook : ebookList) {
//             // EbookResp ebookResp = new EbookResp();
//             // BeanUtils.copyProperties(ebook, ebookResp);
//             EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//
//             respList.add(ebookResp);
//         }

        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        PageResp<EbookResp> pageResp = new PageResp();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    public void save(EbookSaveReq req){

        Ebook ebook =CopyUtil.copy(req,Ebook.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebookMapper.insert(ebook);
        } else {
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }

    }

}
