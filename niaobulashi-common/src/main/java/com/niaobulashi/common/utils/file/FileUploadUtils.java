package com.niaobulashi.common.utils.file;

import com.niaobulashi.common.config.Global;
import com.niaobulashi.common.exception.file.FileNameLengthLimitExceededException;
import com.niaobulashi.common.exception.file.FileSizeLimitExceededException;
import com.niaobulashi.common.exception.file.InvalidExtensionException;
import com.niaobulashi.common.utils.DateUtils;
import com.niaobulashi.common.utils.StringUtils;
import com.niaobulashi.common.utils.security.Md5Utils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

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
     * 以默认配置进行文件上传
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(defaultBaseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     * @param baseDir 相对应的根目录
     * @param file  上传的文件
     * @param allowedExtension  上传的文件后缀
     * @return  返回成功上传的文件名
     * @throws FileSizeLimitExceededException 文件太大异常
     * @throws IOException 读写文件异常
     * @throws FileNameLengthLimitExceededException 文件长度异常
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException,
            FileNameLengthLimitExceededException, InvalidExtensionException {
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        // 校验文件大小
        assertAllowed(file, allowedExtension);
        // 编码文件名
        String fileName = extractFileName(file);
        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    /**
     * 编码文件名
     * @param file
     * @return
     */
    public static final String extractFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + encodingFileName(fileName) + "." + extension;
        return fileName;
    }

    /**
     * 获取带有绝对路径的文件
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File file = new File(uploadDir + File.separator + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
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

    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension, fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension, fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension, fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
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

