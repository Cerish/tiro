package cn.cerish.service;

import cn.cerish.exception.FileException;
import cn.cerish.entity.FileProperties;
import cn.cerish.util.RandowUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Autowired
    private RandowUtils randowUtils;
    private String AVATAR_DIR = "avatar/";
    @Autowired
    private FileProperties fileProperties;

    private final Path fileStorageLocation; // 文件在本地存储的地址

    // 在运行的时候即创建了一个目录
    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // 清理路径 去除 ../
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


        try {
            // 如果不存在该文件夹 则创建
            if(!Files.exists(this.fileStorageLocation.resolve(AVATAR_DIR))) {
                Files.createDirectory(this.fileStorageLocation.resolve(AVATAR_DIR));
            }

            // 文件存储名称： /avatar/随机8位数 + 获取文件后缀名
            String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String avatar = AVATAR_DIR + randowUtils.generate(8) + extName;
            Path targetLocation = this.fileStorageLocation.resolve(avatar);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return avatar;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        String savePath = "";
        try {
            savePath = AVATAR_DIR + fileName;
            Path filePath = this.fileStorageLocation.resolve(savePath).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + savePath);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + savePath, ex);
        }
    }
}