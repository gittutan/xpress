package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.Upload;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadDAO extends BaseMapper<Upload> {

//    List<UploadDetailDTO> selectFileDetail();

}
