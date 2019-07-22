package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.MetaDetailDTO;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.EditMetaParam;
import com.wuyuncheng.xpress.model.param.MetaParam;

import java.util.List;

public interface MetaService {

    List<MetaDetailDTO> listMetas(MetaType metaType);
    void deleteMeta(Integer metaId, MetaType metaType);
    void createMeta(MetaParam metaParam, MetaType metaType);
    MetaDTO findMeta(Integer metaId, MetaType metaType);
    void updateMeta(EditMetaParam editMetaParam, Integer metaId, MetaType metaType);

}
