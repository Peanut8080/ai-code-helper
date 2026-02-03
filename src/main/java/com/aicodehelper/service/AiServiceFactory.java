package com.aicodehelper.service;

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
    @Bean
    public AiCodeHelperService aiCodeHelperService() {
        return AiServices.create(AiCodeHelperService.class, qwenChatModel);
    }
}
