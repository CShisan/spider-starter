package com.shisan.spider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Data
@Component
@PropertySource("classpath:spider.properties")
@ConfigurationProperties(prefix = "spider")
public class SpiderConfig {
    private String url;
    private String template;
    private String filePath;
    private String filePrefix;
    private String fileSuffix;

}
