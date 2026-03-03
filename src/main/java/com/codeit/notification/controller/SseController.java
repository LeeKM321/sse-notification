package com.codeit.notification.controller;

import com.codeit.notification.service.SseEmitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
@RequestMapping("/api/sse")
@RequiredArgsConstructor
public class SseController {

    private final SseEmitterService sseEmitterService;

    // SSE 연결 생성 요청 (Content-Type을 text/event-stream으로 설정합니다.)
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(@RequestParam String userId,
                              @RequestParam(required = false) String lastEventId) {
        log.info("SSE Connecting to user {}", userId);
        return sseEmitterService.createEmitter(userId, lastEventId);
    }

    // SSE 연결 종료 요청
    @PostMapping("/disconnect")
    public void disconnect(@RequestParam String userId) {
        log.info("SSE Connecting to user {}", userId);
        sseEmitterService.closeEmitter(userId);
    }

    @GetMapping("/connection-count")
    public int getConnectionCount() {
        return sseEmitterService.getConnectedUserCount();
    }

    @GetMapping("/is-connected")
    public boolean isConnected(@RequestParam String userId) {
        return sseEmitterService.isConnected(userId);
    }

}

















