package com.superzyen.zyengame.common;

import com.superzyen.zyengame.account.Account;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    public static <T> T deserializeObject(String path, Class<T> clazz) throws ClassNotFoundException, IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            return (T) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            log.error("反序列化失败");
            throw e;
        }
    }

    public static void serializeObject(Object object) throws IOException {
        exist(Account.DEFUALT_DIR_NAME);
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File(getPath() + "/" + Account.DEFUALT_DIR_NAME + "/" + Account.DEFUALT_FILE_NAME)))) {
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
