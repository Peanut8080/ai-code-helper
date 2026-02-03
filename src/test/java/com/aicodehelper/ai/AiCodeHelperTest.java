package com.aicodehelper.ai;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/03/ 20:05
 */
@SpringBootTest
class AiCodeHelperTest {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void testAiCodeHelper() {
        aiCodeHelper.chat("你好，我是一个java程序员");
    }

    @Test
    void testWithUserMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"), ImageContent.from("https://www.codefather.cn/logo.png")
        );
        aiCodeHelper.chat(userMessage);
    }
}