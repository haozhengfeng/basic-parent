package org.haozf.basic.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexUtil {

	private Directory directory;
	private Analyzer analyzer;
	private IndexWriterConfig iwc;
	private IndexWriter writer;
	private static IndexReader reader;
	private IndexSearcher indexSearcher;

	public IndexUtil(String indexPath) {
		try {
			// directory = new RAMDirectory();
			directory = FSDirectory.open(Paths.get(indexPath));
			analyzer = new StandardAnalyzer();
			// analyzer = new SimpleAnalyzer();
			iwc = new IndexWriterConfig(analyzer);
			reader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void index(String filePath) {
		try {
			writer = new IndexWriter(directory, iwc);
			File f = new File(filePath);
			for (int i = 0; i < f.listFiles().length; i++) {
				File file = f.listFiles()[i];
				Document doc = new Document();
				doc.add(new TextField("content", new FileReader(file)));
				Field filenameField = new TextField("filename", file.getName(), Store.YES);
				doc.add(filenameField);
				doc.add(new TextField("path", file.getAbsolutePath(), Store.YES));
				if (file.getName().contains("2")) {
					filenameField.setBoost(2f);
				}
				if (file.getName().contains("3")) {
					filenameField.setBoost(3f);
				}
				if (file.getName().contains("4")) {
					filenameField.setBoost(4f);
				}
				writer.addDocument(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void query() {
		try {
			QueryParser queryParser = new QueryParser("filename", new StandardAnalyzer());
			Query query = queryParser.parse("新");
			TopDocs tDocs = indexSearcher.search(query, 10);
			ScoreDoc[] docs = tDocs.scoreDocs;
			for (ScoreDoc sDoc : docs) {
				Document document = indexSearcher.doc(sDoc.doc);
				System.out.println(document.get("filename") + "  " + document.get("path"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		try {
			writer = new IndexWriter(directory, iwc);
			writer.deleteAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByTerm() {
		try {
			writer = new IndexWriter(directory, iwc);
			writer.deleteDocuments(new Term("filename", "新"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByQuery() {
		try {
			writer = new IndexWriter(directory, iwc);
			QueryParser queryParser = new QueryParser("content", analyzer);
			Query query = queryParser.parse("123123123awsf");
			writer.deleteDocuments(query);
			// writer.commit();
			writer.rollback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void unDelete() {
		try {
			writer = new IndexWriter(directory, iwc);
			writer.rollback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void check() {
		DirectoryReader directoryReader = null;
		try {
			directoryReader = DirectoryReader.open(directory);
			// 通过reader可以有效的获取到文档的数量
			// 有效的索引文档
			System.out.println("有效的索引文档:" + directoryReader.numDocs());
			// 总共的索引文档
			System.out.println("总共的索引文档:" + directoryReader.maxDoc());
			// 删掉的索引文档，其实不恰当，应该是在回收站里的索引文档
			System.out.println("删掉的索引文档:" + directoryReader.numDeletedDocs());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (directoryReader != null) {
					directoryReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		IndexUtil indexUtil = new IndexUtil("d:/lucene/index02");
		// indexUtil.index("D:/lucene/doc");
		// indexUtil.deleteAll();
		// indexUtil.deleteByQuery();
		// indexUtil.unDelete();
		// indexUtil.check();
		indexUtil.query();
		indexUtil.query();
	}
}
