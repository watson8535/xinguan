package com.edward.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AliossService {

    /**
     * 创建存储空间
     */
    void createBucket();

    /**
     * 上传文件
     * @param file 文件对象
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     * @param fileName
     * @throws IOException
     */
    void download(String fileName) throws IOException;

    /**
     * 列举文件
     */
    void listFile();

    /**
     * 删除文件
     * @param fileName
     */
    void deletedFile(String fileName);
}
