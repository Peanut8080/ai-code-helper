package com.aicodehelper.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:29
 */
@Slf4j
@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource(name = "aiCodeHelperServiceWithMemoryId")
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String chat = aiCodeHelperService.chat("你好，我是一个java后端开发程序员");
        log.info("AI:{}", chat);
    }

    @Test
    void chatWithMerry() {
        String chat = aiCodeHelperService.chatWithMemoryId("001", "你好，我是一个java后端开发程序员");
        log.info("AI:{}", chat);
        chat = aiCodeHelperService.chat("我是谁？");
        log.info("AI:{}", chat);
    }
}