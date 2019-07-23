package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.MetaDetailDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.MetaParam;

import java.util.List;

public interface MetaService extends IService<Meta> {

    List<MetaDetailDTO> listMetas(MetaType metaType);
    void deleteMeta(Integer metaId, MetaType metaType);
    void createMeta(MetaParam metaParam, MetaType metaType);
    MetaDTO findMeta(Integer metaId, MetaType metaType);
    void updateMeta(MetaParam metaParam, Integer metaId, MetaType metaType);

}
