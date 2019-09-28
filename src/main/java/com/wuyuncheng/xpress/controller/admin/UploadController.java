package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.model.param.FileParam;
import com.wuyuncheng.xpress.service.UploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation("获取文件列表")
    @GetMapping("/files")
    @ResponseStatus(HttpStatus.OK)
    public List<Upload> listFiles() {
        return uploadService.listFiles();
    }

    @ApiOperation("删除文件")
    @DeleteMapping("/files/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFile(@PathVariable Integer id) {
        Assert.notNull(id, "文件 ID 不能为空");
        uploadService.removeFile(id);
    }

    @ApiOperation("创建文件")
    @PostMapping("/files")
    @ResponseStatus(HttpStatus.CREATED)
    public Upload createFile(@RequestBody @Valid FileParam fileParam) {
        return uploadService.createFile(fileParam);
    }

}
