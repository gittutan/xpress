package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.param.PageParam;

import java.util.List;

public interface PageService {

    List<PageDTO> listPages();
    void removePage(Integer pageId);
    void createPage(PageParam pageParam);
    PageDTO getPage(Integer pageId);
    PageDTO getPageBySlugOrId(String slugOrId);
    void updatePage(PageParam pageParam, Integer pageId);

}
