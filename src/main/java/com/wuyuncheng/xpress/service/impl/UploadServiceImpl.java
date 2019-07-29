package com.wuyuncheng.xpress.service.impl;

import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.exception.FileException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.UploadDAO;
import com.wuyuncheng.xpress.model.dto.UploadDetailDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;
import com.wuyuncheng.xpress.service.UploadService;
import com.wuyuncheng.xpress.util.DateUtils;
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

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDAO uploadDAO;
    @Autowired
    private XPressProperties properties;

    @Override
    public List<UploadDetailDTO> listFiles() {
        return uploadDAO.selectFileDetail();
    }

    @Override
    public void deleteFile(Integer uploadId) {
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
        String filename = DateUtils.nowUnix() + file.getOriginalFilename();
        // 插入数据库
        Upload upload = new Upload();
        upload.setAuthorId(fileParam.getAuthorId());
        upload.setFilename(filename);
        upload.setMimetype(file.getContentType());
        upload.setSize(file.getSize());
        upload.setCreated(DateUtils.nowUnix());
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
            Path path = Paths.get(properties.getUploadPath() + filename);
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new FileException("文件写入失败");
        }
        return upload;
    }

}
