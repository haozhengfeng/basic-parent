package com.lucene.test;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;

public class LuceneUseSimpleAnalyzerTest {
	Directory dir;
	Analyzer analyzer;
	IndexWriterConfig iwc;

	protected void setUp() {
		try {
			String txt = "京华时报1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";
			// txt = "2008年底发了资金吗";
			analyzer = new SimpleAnalyzer();
			analyzer = new ComplexAnalyzer();
			dir = new RAMDirectory();
			iwc = new IndexWriterConfig(analyzer);
			IndexWriter iw = new IndexWriter(dir, iwc);
			Document doc = new Document();
			doc.add(new TextField("txt", txt, Store.YES));
			iw.addDocument(doc);
			iw.commit();
			iw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testSearch() {
		try {
			IndexReader reader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			QueryParser qp = new QueryParser("txt", analyzer);
			Query q = qp.parse("西伯利亚"); // 2008年底
			System.out.println(q);
			TopDocs tds = searcher.search(q, 10);
			System.out.println("======size:" + tds.totalHits + "========");
			for (ScoreDoc sd : tds.scoreDocs) {
				System.out.println(sd.score);
				System.out.println(searcher.doc(sd.doc).get("txt"));
			}
		} catch (CorruptIndexException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LuceneUseSimpleAnalyzerTest lTest = new LuceneUseSimpleAnalyzerTest();
		lTest.setUp();
		lTest.testSearch();
	}

}
