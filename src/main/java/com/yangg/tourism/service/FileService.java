package com.yangg.tourism.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadAvatar(MultipartFile image) throws IOException;
}
