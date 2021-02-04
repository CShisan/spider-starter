package com.shisan.spider.utils;

import com.shisan.spider.config.SpiderConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.shisan.spider.common.Constant.SUCCESS;

/**
 * @author Administrator
 */
@Component
public class HttpToolkit {
    private static CloseableHttpClient httpClient = null;

    public HttpToolkit() {
        init();
    }

    public String getHtml(SpiderConfig spider) throws IOException {
        HttpGet httpGet = new HttpGet(spider.getUrl());
        // 设置请求信息
        httpGet.setConfig(this.requestConfig());
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != SUCCESS || response.getEntity() == null) {
            return null;
        }

        String html = EntityUtils.toString(response.getEntity(), "utf8");
        response.close();
        return html;
    }

    public List<String> analyze(SpiderConfig spider, String html) {
        List<String> list = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select(spider.getTemplate());
        for (Element element : elements) {
            list.add(element.text()+" ");
        }
        return list;
    }

    private void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(100);
        // 设置每台主机最大连接数
        cm.setDefaultMaxPerRoute(10);

        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    private RequestConfig requestConfig() {
        return RequestConfig.custom()
                // 连接超时时间
                .setConnectTimeout(1000)
                // 请求超时时间
                .setConnectionRequestTimeout(500)
                // 数据传输超时时间
                .setSocketTimeout(10 * 1000)
                .build();
    }

}
