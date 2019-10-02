package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.UploadDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;

import java.util.List;

public interface UploadService {

    List<UploadDTO> listFiles();
    void removeFile(Integer uploadId);
    Upload createFile(FileParam fileParam);

}
