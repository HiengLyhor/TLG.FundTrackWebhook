package com.fundtrack.telegram.webhook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramWebhookBot {

    private String botToken = "8201582918:AAEMvuWX7f3VHtFEq5kIxI6Wsn6yArnosI0";

    private String botUsername = "myfinancetrackebot";

    private String webhookPath = "/webhook";

    public TelegramBot() {}

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotPath() {
        return webhookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null; // handled by controller
    }

    public void sendTextMessage(Long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .parseMode("MarkdownV2")
                .build();
        try {
            execute(message);  // Only works here because this is TelegramWebhookBot
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
