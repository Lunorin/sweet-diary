package com.sky.task;

import com.sky.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WebSocketTask {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 通过WebSocket每隔10秒向客户端发送心跳消息（JSON格式，避免前端JSON.parse报错）
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMessageToClient() {
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        // type=0 心跳消息，前端收到后无需处理，仅用于保持连接
        webSocketServer.sendToAllClient("{\"type\":0,\"content\":\"心跳：" + time + "\"}");
    }
}
