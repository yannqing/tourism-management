package com.yangg.tourism.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    /**
     * 上传图片
     * @param image 图片
     * @return 返回图片的路径
     * @throws IOException IO 异常
     */
    String uploadAvatar(MultipartFile image) throws IOException;
}
