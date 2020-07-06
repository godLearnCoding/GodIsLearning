package elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * <pre>
 * 作者：shenliang
 * 项目：elasticsearch
 * 说明：
 * 日期：2020年06月24日
 * 备注：
 * </pre>
 */
public class ElkJavaHighApiDemo {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(
            new HttpHost("localhost", 9200, "http")));

    //索引文档请求
    IndexRequest request = new IndexRequest(
        "posts",
        "doc",
        "1");
    String jsonString = "{" +
        "\"user\":\"kimchy\"," +
        "\"postDate\":\"2013-01-30\"," +
        "\"message\":\"trying out Elasticsearch\"" +
        "}";
    request.source(jsonString, XContentType.JSON);
    request.timeout("1s");

    //IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
    //System.out.println(indexResponse);

    //检索文档请求
    GetRequest getRequest = new GetRequest(
        "posts",
        "doc",
        "1");
    GetResponse documentFields = client.get(getRequest);
    System.out.println(documentFields);
    //删除文档api
    //DeleteRequest  deleteRequest = new DeleteRequest("posts","doc","1");
    //DeleteResponse response  = client.delete(deleteRequest);
    //System.out.println(response);

    //搜索api
    SearchRequest searchRequest = new SearchRequest();
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.termQuery("message","out"));
    sourceBuilder.timeout(new TimeValue(1000));
    searchRequest.source(sourceBuilder);
    searchRequest.indices("posts");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    HighlightBuilder.Field highlightTitle =
        new HighlightBuilder.Field("title");
    highlightTitle.highlighterType("unified");
    highlightBuilder.field(highlightTitle);
    HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("message");
    highlightBuilder.field(highlightUser);
    searchSourceBuilder.highlighter(highlightBuilder);
    SearchResponse searchResponse = client.search(searchRequest);
    System.out.println(searchResponse);
    client.close();
  }

}
