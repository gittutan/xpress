package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.model.dto.UploadDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;

public interface UploadService {

    IPage<UploadDTO> listFiles(IPage<Upload> page);
    void removeFile(Integer uploadId);
    Upload createFile(FileParam fileParam);

}
