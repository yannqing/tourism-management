package com.yangg.tourism.service.impl;

import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${project.upload-url}")
    private String uploadUrl;


    @Override
    public String uploadAvatar(MultipartFile image) throws IOException {
        //参数校验
        if (image == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        //获取文件路径
        String fileName = image.getOriginalFilename();
        if (fileName == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        UUID uuid = UUID.randomUUID();
        String newFilename = replaceFilename(fileName, uuid.toString());
        Path path = Paths.get(uploadUrl + newFilename);
        // 判断 images 文件夹是否存在，不存在则创建
        File file = new File(uploadUrl);
        if (!file.exists()) {
            boolean created = file.mkdir();
            if (created) {
                log.info("文件夹{}创建成功", uploadUrl);
            } else {
                log.info("文件夹{}创建失败", uploadUrl);
            }
        }
        //存储到服务器
        byte[] avatarBytes = image.getBytes();
        Files.write(path, avatarBytes);
        //下载路径
        return "/download/image/"+newFilename;
    }

    /**
     * 自定义方法，用来替换文件名
     * @param filename 原文件名
     * @param newName 新文件名
     * @return 替换结果
     */
    public static String replaceFilename(String filename, String newName) {
        // 使用正则表达式匹配文件名和扩展名部分
        String regex = "(.*)(\\..*)";
        // 将文件名替换为新名称
        return filename.replaceAll(regex, newName + "$2");
    }
}
