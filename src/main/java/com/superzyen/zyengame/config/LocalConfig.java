package com.superzyen.zyengame.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalConfig implements Serializable {
    public static final String DEFUALT_DIR_NAME = "config";
    public static final String DEFUALT_FILE_NAME = "setting.txt";
    private String ip;
    private Integer port;
}
