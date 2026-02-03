package com.aicodehelper.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/03/ 21:39
 */
@Slf4j
@SpringBootTest
class AiServiceCodeHelperWithL4jStaterTest {

    @Resource
    private AiCodeHelperServiceWithL4jStater aiCodeHelperServiceWithL4jStater;

    @Test
    void contextLoads() {
        String chat = aiCodeHelperServiceWithL4jStater.chat("你好，我是一个java后端开发程序员");
        log.info("chat:{}", chat);
    }
}