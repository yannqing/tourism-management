package com.yangg.tourism.controller;

import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.FileService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Tag(name = "文件管理")
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 上传图片接口
     * @param image 图片文件
     * @return
     * @throws IOException
     */
    @Operation(summary = "上传图片")
    @PostMapping("/upload/images")
    public BaseResponse<String> uploadAvatar(@RequestParam("image") MultipartFile image) throws IOException {
        String downloadUrl = fileService.uploadAvatar(image);

        return ResultUtils.success(Code.SUCCESS, downloadUrl, "上传成功！");
    }

    /**
     * 下载文件
     * @param filename
     * @return
     */
    @Operation(summary = "下载图片文件")
        @GetMapping("/download/image/{filename}")
    public ResponseEntity<FileSystemResource> downloadImage(@PathVariable("filename") String filename) {

        String imagePath = "./images/" + filename; // 图片的本地路径

        return getFileSystemResourceResponseEntity(filename, imagePath);
    }


    private ResponseEntity<FileSystemResource> getFileSystemResourceResponseEntity(@PathVariable("filename") String filename, String imagePath) {
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", filename);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new FileSystemResource(imageFile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
