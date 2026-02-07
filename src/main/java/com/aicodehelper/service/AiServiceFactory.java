package com.aicodehelper.service;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明式 AiService创建工厂 对比AiCodeHelper编程式创建；声明式代码更简洁，但编程式更灵活。建议使用该方式
 * 与AiCodeHelperService结合使用
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:26
 */
@Configuration
public class AiServiceFactory {

    @Resource
    ChatModel myQwenChatModel;

    @Resource
    ContentRetriever contentRetriever;

    @Resource
    McpToolProvider mcpToolProvider;

    /**
     * 注入流式对话模型
     */
    @Resource
    StreamingChatModel qwenStreamingChatModel;

    /**
     * 这里底层通过反射+动态代理的方式 创建一个AIService，带有对应的系统预设提示词
     *
     * @return AiCodeHelperService
     */
    @Bean("aiCodeHelperService")
    public AiCodeHelperService aiCodeHelperService() {
        // MessageWindowChatMemory 基于消息窗口的message；这里设置每个用户最大保存十条会话记忆
        // 也可以自定义一个ChatMemoryStore类，来持久化会话记忆（即增删改查）--进阶
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造AIService 一种是直接创建
//        AiServices.create(AiCodeHelperService.class, qwenChatModel)
        // 一种是使用构造者模式创建
        return AiServices
                .builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                // 设置会话记忆
                .chatMemory(chatMemory)
                // RAG 检索增强生成
                .contentRetriever(contentRetriever)
                // MCP 工具调用
                .toolProvider(mcpToolProvider)
                .build();
    }

    @Bean("aiCodeHelperServiceWithMemoryId")
    public AiCodeHelperService aiCodeHelperServiceWithMerryId() {
        return AiServices
                .builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                // 设置会话记忆
                .chatMemoryProvider(merryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @Bean("aiCodeHelperServiceWithStreaming")
    public AiCodeHelperService aiCodeHelperServiceWithStreaming() {
        return AiServices
                .builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                // 设置会话记忆
                .chatMemoryProvider(merryId -> MessageWindowChatMemory.withMaxMessages(10))
                // RAG 检索增强生成
                .contentRetriever(contentRetriever)
                // MCP 工具调用
                .toolProvider(mcpToolProvider)
                .build();
    }
}
