package com.fundtrack.telegram.webhook.handler;

import com.fundtrack.telegram.webhook.config.TelegramBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TextMessageHandler implements IUpdateHandler{

    @Override
    public boolean supports(Update update) {

        return update.hasMessage()
                && update.getMessage().hasText()
                && "private".equals(update.getMessage().getChat().getType());

    }

    @Override
    public void handle(Update update) {

        GenAIHandler aiHandler = new GenAIHandler();
        String aiResponse = aiHandler.responseMessage(update);

        if (aiResponse != null) {
            ResponseMessageHandler responseHandler = new ResponseMessageHandler(new TelegramBot());
            responseHandler.responseMessage(update.getMessage().getChatId(), aiResponse);
        }
    }

}
