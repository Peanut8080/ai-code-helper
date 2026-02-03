package com.aicodehelper.ai;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
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
     * 系统预设 也叫系统提示词
     */
    private static final String SYSTEM_MESSAGE = """
            你是编程领域的小助手，帮助用户解答编程学习和求职面试相关的问题，并给出建议。重点关注 4 个方向：
            1. 规划清晰的编程学习路线
            2. 提供项目学习建议
            3. 给出程序员求职全流程指南（比如简历优化、投递技巧）
            4. 分享高频面试题和面试技巧
            请用简洁易懂的语言回答，助力用户高效学习与求职。
            """;

    /**
     * chat方法 调用AI
     *
     * @param useMessage 用户输入消息
     * @return AI 返回消息
     */
    public String chat(String useMessage) {
        // 组装调用 AI 参数
        UserMessage userMessage = UserMessage.from(useMessage);
        return chat(userMessage);
    }

    /**
     * 多模态对话
     * 注意，不是所有的模型都支持多模态
     *
     * @param userMessage userMessage
     * @return AI 返回消息
     */
    public String chat(UserMessage userMessage) {
        // 设置多个系统消息无效 新的会代替旧的
        ChatResponse chatRes = qwenChatModel.chat(SystemMessage.from(SYSTEM_MESSAGE), userMessage);
        // 获取 AI 返回的消息
        AiMessage aiMessage = chatRes.aiMessage();
        log.debug("AI返回结果：{}", aiMessage);
        return aiMessage.toString();
    }
}
