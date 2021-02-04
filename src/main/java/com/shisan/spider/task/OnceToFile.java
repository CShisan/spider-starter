package com.shisan.spider.task;

import com.shisan.spider.config.SpiderConfig;
import com.shisan.spider.utils.FileToolkit;
import com.shisan.spider.utils.HttpToolkit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 */
@Component        //该注解表明该类被Spring容器管理
@Order(1)        //如果有多个自定义的ApplicationRunner，该注解用来表明执行的顺序
public class OnceToFile implements ApplicationRunner {
    @Resource
    private SpiderConfig spider;
    @Resource
    private HttpToolkit httpToolkit;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String html = httpToolkit.getHtml(spider);
        List<String> list = httpToolkit.analyze(spider, html);
        StringBuilder content = new StringBuilder();
        for (String item : list) {
            String temp = item + " ";
            content.append(temp);
        }
        FileToolkit.genFile(spider, content.toString());
    }
}
