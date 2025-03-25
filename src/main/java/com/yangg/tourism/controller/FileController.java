package com.yangg.tourism.controller;

import com.yangg.tourism.annotation.AuthCheck;
import com.yangg.tourism.common.Code;
import com.yangg.tourism.domain.model.BaseResponse;
import com.yangg.tourism.service.FileService;
import com.yangg.tourism.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @AuthCheck(code = "FILE_UPLOAD")
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
    @AuthCheck(code = "FILE_DOWNLOAD")
    @Operation(summary = "下载图片文件")
    @GetMapping("/download/image/{filename}")
    public ResponseEntity<FileSystemResource> downloadImage(@PathVariable("filename") String filename) {

        String imagePath = "./images/" + filename; // 图片的本地路径

        return getFileSystemResourceResponseEntity(filename, imagePath);
    }


    private ResponseEntity<FileSystemResource> getFileSystemResourceResponseEntity(@PathVariable("filename") String filename, String imagePath) {
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            // 根据文件扩展名设置正确的 Content-Type
            String contentType = determineContentType(filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType)); // 设置正确的 Content-Type
            headers.setContentDispositionFormData("inline", filename); // 使用 "inline" 而不是 "attachment"

            // 设置缓存相关的头
            headers.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS).cachePublic().getHeaderValue());
            headers.setExpires(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)); // 7 天缓存

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new FileSystemResource(imageFile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据文件扩展名确定 Content-Type
    private String determineContentType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else if (filename.endsWith(".gif")) {
            return "image/gif";
        } else if (filename.endsWith(".webp")) {
            return "image/webp";
        } else {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE; // 默认返回二进制流
        }
    }
}
