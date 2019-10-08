package com.wuyuncheng.xpress.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.model.dto.UploadDTO;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;
import com.wuyuncheng.xpress.service.UploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("ApiUploadController")
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation("获取文件列表")
    @GetMapping("/files")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UploadDTO> listFiles(@RequestParam("page") Integer pageNum,
                                      @RequestParam("size") Integer pageSize) {
        IPage<Upload> page = new Page<>(pageNum, pageSize);
        return uploadService.listFiles(page);
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/files/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFile(@PathVariable Integer id) {
        uploadService.removeFile(id);
    }

    @ApiOperation("创建文件")
    @PostMapping("/files")
    @ResponseStatus(HttpStatus.CREATED)
    public Upload createFile(@Valid FileParam fileParam) {
        return uploadService.createFile(fileParam);
    }

}
