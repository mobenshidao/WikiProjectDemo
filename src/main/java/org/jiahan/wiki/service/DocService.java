package org.jiahan.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Doc;
import org.jiahan.wiki.domain.DocExample;
import org.jiahan.wiki.mapper.DocMapper;
import org.jiahan.wiki.req.DocQueryReq;
import org.jiahan.wiki.req.DocSaveReq;
import org.jiahan.wiki.resp.DocQueryResp;
import org.jiahan.wiki.resp.PageResp;
import org.jiahan.wiki.utils.CopyUtil;
import org.jiahan.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    public SnowFlake snowFlake;

    public List<DocQueryResp> all(){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        //copy
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        return list;

    }
    public PageResp<DocQueryResp> list(DocQueryReq req){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docList);
        LOG.info("Rows: {}",pageInfo.getTotal());
        LOG.info("Pages: {}",pageInfo.getPages());

//        List<DocResp> respList = new ArrayList<>();

//         for (Doc doc : docList) {
//             // DocResp docResp = new DocResp();
//             // BeanUtils.copyProperties(doc, docResp);
//             DocResp docResp = CopyUtil.copy(doc, DocResp.class);
//
//             respList.add(docResp);
//         }

        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        PageResp<DocQueryResp> pageResp = new PageResp();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    public void save(DocSaveReq req){

        Doc doc =CopyUtil.copy(req,Doc.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        } else {
            // 更新
            docMapper.updateByPrimaryKey(doc);
        }

    }

    public void delete(Long id){

        docMapper.deleteByPrimaryKey(id);
        LOG.info("SerID="+id);

    }
    public void delete(List<Long> ids){

        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);

    }

}
