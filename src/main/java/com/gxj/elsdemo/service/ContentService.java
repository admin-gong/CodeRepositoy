package com.gxj.elsdemo.service;

import com.alibaba.fastjson.JSON;
import com.gxj.elsdemo.pojo.Content;
import com.gxj.elsdemo.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    //1，解析数据放入es 索引中
    public Boolean parseContent(String Keywords) throws  Exception{
        List<Content> contents = new HtmlParseUtil().parseJd(Keywords);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i=0;i<contents.size();i++){
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(contents.get(i)), XContentType.JSON));

        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
               return !bulk.hasFailures();
    }
  //获取这些数据
    public List<Map<String,Object>> searchPage(String keywords,int pageNo,int pageSize) throws IOException {
        if(pageNo<=1){
            pageNo=1;
        }
        SearchRequest jd_goods = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精准查找
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keywords);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        jd_goods.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(jd_goods, RequestOptions.DEFAULT);

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        //解析结果
        for(SearchHit searchHit : search.getHits().getHits()){

            list.add(searchHit.getSourceAsMap());

        }
        return list;

    }



}


