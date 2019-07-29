package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.UploadDetailDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;

import java.util.List;

public interface UploadService {

    List<UploadDetailDTO> listFiles();
    void deleteFile(Integer uploadId);
    Upload createFile(FileParam fileParam);

}
