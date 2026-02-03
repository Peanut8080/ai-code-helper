package com.aicodehelper.service;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明式 AiService创建工厂 对比AiCodeHelper编程式创建；声明式代码更简洁，但编程式更灵活。建议使用该方式
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:26
 */
@Configuration
public class AiServiceFactory {

    @Resource
    ChatModel qwenChatModel;

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
                .chatModel(qwenChatModel)
                // 设置会话记忆
                .chatMemory(chatMemory)
                .build();
    }

    @Bean("aiCodeHelperServiceWithMemoryId")
    public AiCodeHelperService aiCodeHelperServiceWithMerryId() {
        return AiServices
                .builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                // 设置会话记忆
                .chatMemoryProvider(merryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }
}
