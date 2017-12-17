package com.lucene.test;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class HelloLucene {

	/**
	 * 建立索引
	 */
	@Test
	public void index() {
		IndexWriter writer = null;
		try {
			// 1、创建Directory
			// Directory directory = new RAMDirectory();
			Directory directory = FSDirectory.open(Paths.get("d:/lucene/index01"));

			// 2、创建IndexWriter
			// 标准分词
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			writer = new IndexWriter(directory, iwc);

			File f = new File("D:/logs");
			for (File file : f.listFiles()) {
				// 3、创建document对象
				Document doc = new Document();
				// 4、为document添加field
				System.out.println(new FileReader(file));
				/**
				 * 自Lucene4开始 创建field对象使用不同的类型 只需要指定是否需要保存源数据 不需指定分词类别 之前版本的写法如下
				 * doc.Add(new Field("id", item.id.ToString(), Field.Store.YES,
				 * Field.Index.ANALYZED));
				 */
				doc.add(new TextField("content", new FileReader(file)));
				doc.add(new StringField("filename", file.getName(), Store.YES));
				doc.add(new StringField("path", file.getAbsolutePath(), Store.YES));
				// 5、通过indexwriter添加文档到索引
				writer.addDocument(doc);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void search() {
		try {
			// 1、创建directory
			Directory directory = FSDirectory.open(Paths.get("d:/lucene/index01"));
			// 2、创建IndexReader
			IndexReader reader = DirectoryReader.open(directory);
			// 3、根据IndexReader创建IndexSercher
			IndexSearcher indexSearcher = new IndexSearcher(reader);
			// 4、创建搜索query
			// 创建parser来确定要
			QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
			String queryString = "username";
			Query query = queryParser.parse(queryString);

			TopDocs tDocs = indexSearcher.search(query, 10);
			ScoreDoc[] docs = tDocs.scoreDocs;
			System.out.println(docs.length);
			for (ScoreDoc sDoc : docs) {
				System.out.println("序号为：" + sDoc.doc);
				System.out.println("评分为：" + sDoc.score);
				Document document = indexSearcher.doc(sDoc.doc);
				System.out.println(document.get("filename") + "  " + document.get("path"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
