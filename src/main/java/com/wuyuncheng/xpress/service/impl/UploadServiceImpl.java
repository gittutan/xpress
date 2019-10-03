package com.wuyuncheng.xpress.service.impl;

import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.exception.FileException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.UploadDAO;
import com.wuyuncheng.xpress.model.dto.UploadDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;
import com.wuyuncheng.xpress.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDAO uploadDAO;
    @Autowired
    private XPressProperties properties;

    @Override
    public List<UploadDTO> listFiles() {
        List<Upload> uploads = uploadDAO.selectList(null);
        List<UploadDTO> uploadDTOList = uploads.stream()
                .map(item -> UploadDTO.convertFrom(item))
                .collect(Collectors.toList());
        return uploadDTOList;
    }

    @Transactional
    @Override
    public void removeFile(Integer uploadId) {
        Upload upload = uploadDAO.selectById(uploadId);
        if (null == upload) {
            throw new NotFoundException("文件不存在");
        }
        String filename = upload.getFilename();
        File file = new File(properties.getUploadPath() + filename);
        boolean delete = file.delete();
        if (!delete) {
            throw new FileException("文件删除失败");
        }
        int row = uploadDAO.deleteById(uploadId);
        Assert.state(row != 0, "文件删除失败");
    }

    @Transactional
    @Override
    public Upload createFile(FileParam fileParam) {
        MultipartFile file = fileParam.getFile();
        Upload upload = fileParam.convertTo();
        // 插入数据库
        uploadDAO.insert(upload);
        // 写出文件
        if (file.isEmpty()) {
            throw new FileException("上传文件为空");
        }
        try {
            File uploadPath = new File(properties.getUploadPath());
            if (!uploadPath.exists()) {
                uploadPath.mkdir();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(properties.getUploadPath() + upload.getFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new FileException("文件写入失败");
        }
        return upload;
    }

}
