package com.lucene.test;

import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TxtFileIndexer {
	public static void main(String[] args) throws Exception {
		// indexDir is the directory that hosts Lucene's index files
		Directory indexDir = FSDirectory.open(Paths.get("d:\\lucene\\index03"));
		// dataDir is the directory that hosts the text files that to be indexed
		File dataDir = new File("D:\\lucene\\doc");
		Analyzer luceneAnalyzer = new StandardAnalyzer(); // 新建一个分词器实例
		IndexWriterConfig config = new IndexWriterConfig(luceneAnalyzer);
		File[] dataFiles = dataDir.listFiles(); // 所有训练样本文件
		IndexWriter indexWriter = new IndexWriter(indexDir, config);// 构造一个索引写入器
		long startTime = new Date().getTime();
		for (int i = 0; i < dataFiles.length; i++) {
			if (dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")) {
				System.out.println("Indexing file " + dataFiles[i].getCanonicalPath()); // 返回绝对路径
				Document document = new Document();// 每一个文件都变成一个document对象
				Reader txtReader = new FileReader(dataFiles[i]);
				Field field1 = new StringField("path", dataFiles[i].getPath(), Store.YES);
				Field field2 = new TextField("content", txtReader);
				Field field3 = new LongField("fileSize", dataFiles[i].length(), Store.YES);
				Field field4 = new TextField("filename", dataFiles[i].getName(), Store.YES);
				document.add(field1);
				document.add(field2);
				document.add(field3);
				document.add(field4);
				indexWriter.addDocument(document); // 写进一个索引
			}
		}
		// indexWriter.optimize();
		indexWriter.close();
		long endTime = new Date().getTime();

		System.out.println("It takes " + (endTime - startTime) + " milliseconds to create index for the files in directory " + dataDir.getPath());
	}
}
