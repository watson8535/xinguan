package com.edward.system.controller;

import com.edward.response.Result;
import com.edward.system.service.AliossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api
@RestController // 返回json
//@RequestMapping("/userimg")
//@CrossOrigin
public class AliossController {
    @Autowired
    private AliossService aliossService;

    @PostMapping("/uploadImage")
    @ApiOperation(value = "头像上传", tags = "上传头像")
    public Result uploadImg(MultipartFile file){
        String imgurl = aliossService.upload(file);
        return Result.ok().data("url", imgurl);
    }

    @PostMapping("/deleteImgFile")
    @ApiOperation(value = "删除头像", tags = "删除头像")
    public Result deleteImgFile(String file){
        try {
            String[] split = file.split(".com/");
            aliossService.deletedFile(split[1]);
            return Result.ok();
        }catch (Exception e){
            return Result.error();
        }

    }
}
