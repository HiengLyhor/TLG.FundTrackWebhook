package com.fundtrack.telegram.webhook.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IUpdateHandler {

    boolean supports(Update update);
    void handle(Update update);

}
