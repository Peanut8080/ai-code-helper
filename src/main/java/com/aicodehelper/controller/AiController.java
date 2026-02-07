package com.aicodehelper.controller;

import com.aicodehelper.entity.request.ChatRequest;
import com.aicodehelper.service.AiCodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * AI WEB 客户端
 *
 * @author tanghua
 * @date: 2026/02/06/ 13:44
 */

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource(name = "aiCodeHelperServiceWithStreaming")
    private AiCodeHelperService aiCodeHelperService;


    @PostMapping(value = "/chat", consumes = "application/json", produces = "application/json")
    public Flux<ServerSentEvent> chat(@RequestBody ChatRequest chatRequest) {
        return aiCodeHelperService.chatWithStreaming(chatRequest.getMemoryId(), chatRequest.getMessage())
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }
}
