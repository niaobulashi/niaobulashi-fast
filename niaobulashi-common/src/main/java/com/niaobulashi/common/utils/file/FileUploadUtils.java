package com.niaobulashi.common.utils.file;

import com.niaobulashi.common.config.Global;
import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.common.utils.security.Md5Utils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: niaobulashi-fast
 * @description: 文件上传工具类
 * @author: hulang  hulang6666@qq.com
 * @create: 2019-08-13 19:17
 */
public class FileUploadUtils {

    /** 默认上传文件大小 */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /** 默认文件名最长长度 */
    public static final long DEFAULT_FILE_NAME_LENGTH = 100;

    /** 默认上传文件存放路径 */
    private static String defaultBaseDir = Global.getUploadPath();

    private static int counter = 0;

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    /**
     * 获取带有路径的文件
     * @param uploadDir
     * @param fileName
     * @return 返回带有路径的文件
     * @throws IOException
     */
    private static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        int dirLastIndex = uploadDir.lastIndexOf("/") + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = "/profile" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 编码文件名
     * @param fileName
     * @return
     */
    private static final String encodingFileName(String fileName) {
        fileName = fileName.replace("_", " ");
        fileName = Md5Utils.hash(fileName + System.nanoTime() + counter++);
        return fileName;
    }

    /**
     * 获取文件名的后缀
     * @param file
     * @return
     */
    private static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}

