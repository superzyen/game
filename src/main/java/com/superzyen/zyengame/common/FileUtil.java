package com.superzyen.zyengame.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 反序列化
     */
    public static <T> T deserializeObject(String path, Class<T> clazz) throws ClassNotFoundException, IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            return (T) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            log.error("反序列化失败");
            throw e;
        }
    }

    /**
     *  将对象序列化到指定路径
     */
    public static void serializeObject(Object object, String dir, String file) throws Exception {
        if (StringUtils.isBlank(dir) || StringUtils.isBlank(file)) {
            throw new Exception("param have not be null");
        }
        exist(dir);
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File(getPath() + "/" + dir + "/" + file)))) {
            oo.writeObject(object);
        } catch (IOException e) {
            log.error("序列化失败");
            throw e;
        }
    }

    public static String getPath() {
        return System.getProperty("user.dir");
    }

    public static void exist(String folder) {
        String path = getPath() + "/" + folder;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
