package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;
import java.util.List;

/**
 * <pre>
 * 作者：shenliang
 * 项目：lucene
 * 说明：lucene　API
 * 日期：2020年06月23日
 * 备注：
 * </pre>
 */
public class LuceneDemo {

  private static final String indexPath = "F:\\luceneindex\\product";

  public static  void writeIndex(){
    try {
      Directory directory = FSDirectory.open(Paths.get(indexPath));
      Analyzer analyzer = new StandardAnalyzer();
      IndexWriterConfig config = new IndexWriterConfig(analyzer);
      IndexWriter writer = new IndexWriter(directory,config);
      writer.close();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public static void indexDoc(String indexPath, String json){
    try {
      Directory directory = FSDirectory.open(Paths.get(indexPath));
      //标准分词器
      Analyzer analyzer = new StandardAnalyzer();
      //索引创建器配置
      IndexWriterConfig config = new IndexWriterConfig(analyzer);
      IndexWriter writer = new IndexWriter(directory,config);
      Document doc = jsonToDoc(json);
      //索引中添加文件
      doc.add(new TextField("filename","This is the text to be indexed，sss is not a standard word.",Field.Store.YES));
      //写入索引
      writer.addDocument(doc);
      writer.close();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  //搜索索引
  public static String query(String queryStr){
    String result = null;
    try {
      Directory directory = FSDirectory.open(Paths.get(indexPath));
      //分词器
      Analyzer analyzer = new StandardAnalyzer();
      IndexReader reader = DirectoryReader.open(directory);
      //搜索器
      IndexSearcher searcher = new IndexSearcher(reader);
      //查询解析器
      QueryParser queryParser = new QueryParser("filename",analyzer);
      //查询内容
      Query query = queryParser.parse(queryStr);
      //执行搜索
      TopDocs hits = searcher.search(query, 10);
      //解析命中文档
      for(ScoreDoc scoreDoc: hits.scoreDocs){
        Document hitDoc = searcher.doc(scoreDoc.doc);
        List<IndexableField> fields = hitDoc.getFields();
        for(IndexableField field : fields){
          String s = field.stringValue();
          System.out.println(s);
        }
        result = hitDoc.get("filename");
      }
      reader.close();
      directory.close();
    } catch (Exception e){
      e.printStackTrace();
    }
 return result;
  }

  private static Document jsonToDoc(String json) {
    Document document = new Document();
    return document;
  }

  public static void main(String[] args) {
    //创建索引
    writeIndex();
    //增加文档数据
    indexDoc(indexPath,null);
    String aThis = query("sss");
    System.out.println("hidDoc = "+aThis);

  }

}
