package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

/**
 * <pre>
 * 作者：shenliang
 * 项目：lucene
 * 说明：自定义分词器
 * 日期：2020年06月24日
 * 备注：
 * </pre>
 */
public class MyAnalyzer extends Analyzer {

  @Override
  protected TokenStreamComponents createComponents(String s) {
    return new TokenStreamComponents(new WhitespaceTokenizer());
  }
}
