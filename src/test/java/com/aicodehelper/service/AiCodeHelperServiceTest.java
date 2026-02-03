package com.aicodehelper.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:29
 */
@Slf4j
@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String chat = aiCodeHelperService.chat("你好，我是一个java后端开发程序员");
        log.info("chat:{}", chat);
    }

}