package com.aicodehelper.service;

import dev.langchain4j.http.client.sse.ServerSentEvent;
import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:29
 */
@Slf4j
@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource(name = "aiCodeHelperServiceWithStreaming")
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
    void chatWithResult() {
        Result<String> result = aiCodeHelperService.chatWithResult("什么是 Vibe Coding，有哪些推荐资源？");
        log.info("AI:{}", result.content());
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

    @Test
    void chatWithStreaming() {
        Flux<String> result = aiCodeHelperService.chatWithStreaming("001", "你好，我是一个java后端开发程序员");
        Flux<ServerSentEvent> data = result.map(chunk -> new ServerSentEvent("data", chunk));
        log.info("AI:{}", data);
    }
}