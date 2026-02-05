package com.aicodehelper.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * AI编程助手 配置、提供AI对话服务 对比AiCodeHelperService，AiServiceFactory更简洁，更快速；不建议使用该方式，不易做配置，太死板不灵活
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:38
 */
@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
public interface AiCodeHelperServiceWithL4jStater {

    /**
     * 定义接口，定义接口传参类型
     * 注意：这里通过文件来编写提示词，避免提示词过长
     *
     * @param message 用户消息
     * @return AI 返回消息
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String message);
}
