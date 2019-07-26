package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PageDetailDTO;
import com.wuyuncheng.xpress.model.param.PageParam;

import java.util.List;

public interface PageService {

    List<PageDetailDTO> listPages();
    void deletePage(Integer pageId);
    void createPage(PageParam pageParam);
    PageDTO findPage(Integer pageId);
    void updatePage(PageParam pageParam, Integer pageId);

}
