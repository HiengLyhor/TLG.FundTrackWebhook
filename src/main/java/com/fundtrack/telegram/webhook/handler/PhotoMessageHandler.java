package com.fundtrack.telegram.webhook.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class PhotoMessageHandler implements IUpdateHandler{

    @Override
    public boolean supports(Update update) {
        return false;
    }

    @Override
    public void handle(Update update) {

    }

}
