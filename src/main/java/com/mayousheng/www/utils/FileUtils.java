package com.mayousheng.www.utils;

import java.io.*;

/**
 * Created by marking on 2017/4/30.
 */
public class FileUtils {

    private static FileUtils fileUtils;

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        if (fileUtils == null) {
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    public String pathToString(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream = null;
        byte[] buffer = new byte[8192];
        int count;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream = new FileInputStream(file);
            while ((count = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, count);
            }
        } catch (Exception e) {
        } finally {
            closeSilently(byteArrayOutputStream);
            closeSilently(inputStream);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        return data == null ? null : new String(data);
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

}
