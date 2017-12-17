package com.lucene.test;

import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

public class TxtFileSearcher {
	public static void main(String[] args) throws Exception {
		// 存储了索引文件
		Directory indexDir = FSDirectory.open(Paths.get("d:\\lucene\\index02"));
		// 读取器读取索引文件
		DirectoryReader ireader = DirectoryReader.open(indexDir);
		// 查找
		IndexSearcher searcher = new IndexSearcher(ireader);
		// 目的查找字符串
		String queryStr = "awsf";
		// 构造一个词法分析器，并将查询结果返回到一个队列
		QueryParser parser = new QueryParser("content", new StandardAnalyzer());
		Query query = parser.parse(queryStr);
		TopDocs docs = searcher.search(query, 100);
		System.out.print("一共搜索到结果：" + docs.totalHits + "条");
		// 输出查询结果信息
		for (ScoreDoc scoreDoc : docs.scoreDocs) {
			System.out.print("序号为:" + scoreDoc.doc);
			System.out.print("评分为:" + scoreDoc.score);
			Document document = searcher.doc(scoreDoc.doc);
			System.out.print("路径为:" + document.get("path"));
			System.out.print("内容为" + document.get("content"));
			System.out.print("文件大小为" + document.get("fileSize"));
			System.out.print("文件名为" + document.get("filename"));
			System.out.println();
		}
	}
}
