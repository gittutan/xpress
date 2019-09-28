package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.MetaParam;

import java.util.Collection;
import java.util.List;

public interface MetaService extends IService<Meta> {

    List<MetaDTO> listMetas(MetaType metaType);
    void removeMeta(Integer metaId, MetaType metaType);
    void createMeta(MetaParam metaParam, MetaType metaType);
    MetaDTO getMeta(Integer metaId, MetaType metaType);
    void updateMeta(MetaParam metaParam, Integer metaId, MetaType metaType);
    boolean decrementCountById(Integer metaId);
    boolean decrementCountByPostId(Integer postId);
    boolean incrementCountByName(Collection<String> metaNames);

}
