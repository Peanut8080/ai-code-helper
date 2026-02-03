package com.aicodehelper.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * AI编程助手 配置、提供AI对话服务
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:19
 */
public interface AiCodeHelperService {

    /**
     * 定义接口，定义接口传参类型
     * 注意：这里通过文件来编写提示词，避免提示词过长
     *
     * @param message 用户消息
     * @return AI 返回消息
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String message);

    /**
     * 隔离会话
     *
     * @param memoryId 会话Id
     * @param message  用户信息
     * @return AI 返回结果
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    String chatWithMemoryId(@MemoryId String memoryId, @UserMessage String message);
}
