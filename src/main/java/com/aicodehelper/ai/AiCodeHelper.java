package com.aicodehelper.ai;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author tanghua
 * @description AI编程助手 配置、提供AI对话服务
 * @date: 2026/02/03/ 19:54
 */
@Service
@Slf4j
public class AiCodeHelper {

    // 注入ChatModel  AI 大模型交互对象
    @Resource
    private QwenChatModel qwenChatModel;

    /**
     * chat方法 调用AI
     *
     * @param useMessage 用户输入消息
     * @return AI 返回消息
     */
    public String chat(String useMessage) {
        // 组装调用 AI 参数
        UserMessage userMessage = UserMessage.from(useMessage);
        ChatResponse chatRes = qwenChatModel.chat(userMessage);
        // 获取 AI 返回的消息
        AiMessage aiMessage = chatRes.aiMessage();
        log.debug("AI返回结果：{}", aiMessage);
        return aiMessage.toString();
    }
}
