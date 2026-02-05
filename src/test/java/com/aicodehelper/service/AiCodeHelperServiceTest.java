package com.aicodehelper.service;

import dev.langchain4j.service.Result;
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

    @Resource(name = "aiCodeHelperService")
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String chat = aiCodeHelperService.chat("你好，我是一个java后端开发程序员");
        log.info("AI:{}", chat);
    }

    @Test
    void chatWithRag() {
        String chat = aiCodeHelperService.chat("什么是 Vibe Coding，有哪些推荐资源？");
        log.info("AI:{}", chat);
    }

    @Test
    void charWithResult() {
        Result<String> result = aiCodeHelperService.charWithResult("什么是 Vibe Coding，有哪些推荐资源？");
        log.info("AI:{}", result.content());
        log.info("AI:{}", result.sources());
    }

    @Test
    void chatWithMerry() {
        String chat = aiCodeHelperService.chatWithMemoryId("001", "你好，我是一个java后端开发程序员");
        log.info("AI:{}", chat);
        chat = aiCodeHelperService.chat("我是谁？");
        log.info("AI:{}", chat);
    }

    @Test
    void chatWithMcp() {
        String chat = aiCodeHelperService.chat("什么是程序员鱼皮");
        log.info("AI:{}", chat);
    }
}