package com.aicodehelper.ai;

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
        aiCodeHelper.chat("你好，今天是星期几？");
    }
}