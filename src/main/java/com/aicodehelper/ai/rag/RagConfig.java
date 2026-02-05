package com.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * RAG 配置类
 *
 * @author tanghua
 * @date: 2026/02/05/ 18:12
 */
@Configuration
public class RagConfig {

    /**
     * 注入向量模型
     **/
    @Resource
    EmbeddingModel qwenEmbeddingModel;

    /**
     * 基础内存的向量存储
     **/
    @Resource
    EmbeddingStore<TextSegment> embeddingStore;

    /**
     * 创建文件加载器
     **/
    @Bean
    public ContentRetriever contentRetriever() {
        // 1、获取文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        // 2、切割文档 每个文档按照段落切割，最大1000个字符，允许每次200字符重叠
        DocumentSplitter documentSplitter = new DocumentByParagraphSplitter(1000, 200);
        // 3、自定义文档加载器，向量化文档内容并存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(qwenEmbeddingModel)
                .documentSplitter(documentSplitter)
                // 为提高文档质量，未每个切割后的文本碎片 TextSegment 添加文档名称作为元信息
                .textSegmentTransformer(textSegment ->
                        // 文本碎片元文件名称+文本碎片内容
                        TextSegment.from(textSegment.metadata().getString("file_name") + "\n" + textSegment.text(), textSegment.metadata()))
                .embeddingStore(embeddingStore)
                .build();
        // 加载文档
        ingestor.ingest(documents);
        // 4、自定义文档检索器
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                // 检索条件-最多返回几条结果
                .maxResults(5)
                // 检索条件-过滤阈值
                .minScore(0.5)
                .build();
        return contentRetriever;
    }
}
