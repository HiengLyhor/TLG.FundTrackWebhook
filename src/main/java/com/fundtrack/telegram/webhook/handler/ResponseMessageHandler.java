package com.fundtrack.telegram.webhook.handler;

import com.fundtrack.telegram.webhook.config.TelegramBot;
import org.springframework.stereotype.Service;

@Service
public class ResponseMessageHandler {

    private final TelegramBot telegramBot;

    public ResponseMessageHandler(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void responseMessage(Long chatId, String message) {

        try {
            telegramBot.sendTextMessage(chatId, message);
        }
        catch (Exception e) {
            System.out.println("An Error Occurred in ResponseMessageHandler.responseMessage: " + e.getMessage());
        }

    }

}
