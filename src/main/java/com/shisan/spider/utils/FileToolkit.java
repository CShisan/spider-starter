package com.shisan.spider.utils;

import com.shisan.spider.config.SpiderConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 */
@Component
public class FileToolkit {

    public static void genFile(SpiderConfig spider, String data) {
        try {
            String fileName = spider.getFilePrefix() + getTime() + spider.getFileSuffix();
            System.out.println(fileName);
            File file = new File(spider.getFilePath()+fileName);
            FileUtils.writeStringToFile(file, data, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTime() {
        long now = System.currentTimeMillis();
        return DateFormatUtils.format(now, "yyyyMMddHHmmss");
    }
}
