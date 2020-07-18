package com.gxj.elsdemo.util;

import com.gxj.elsdemo.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args)  throws Exception {
        new HtmlParseUtil().parseJd("hello").forEach(System.out::println);
    }

    public List<Content> parseJd(String keywords) throws Exception {

        String url = "https://search.jd.com/Search?keyword="+keywords;
        //返回一个js对象
        Document parse = Jsoup.parse(new URL(url), 30000);
        Element elementById = parse.getElementById("J_goodsList");
        //System.out.println(elementById.html());
        Elements li = elementById.getElementsByTag("li");
        ArrayList<Content> contents = new ArrayList<>();
        for(Element el : li){
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            System.out.println("====");
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            contents.add(content);
        }
        return contents;
    }
}
