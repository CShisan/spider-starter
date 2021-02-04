package com.shisan.spider;

import com.shisan.spider.config.SpiderConfig;
import com.shisan.spider.utils.FileToolkit;
import com.shisan.spider.utils.HttpToolkit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {
    @Resource
    private SpiderConfig spider;
    @Resource
    private HttpToolkit httpToolkit;


    @Test
    public void spiderTest() throws IOException {
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
