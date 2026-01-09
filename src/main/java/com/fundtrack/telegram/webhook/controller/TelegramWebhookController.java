package com.fundtrack.telegram.webhook.controller;

import com.fundtrack.telegram.webhook.dispatcher.UpdateDispatcherService;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/webhook")
public class TelegramWebhookController {

    private final UpdateDispatcherService dispatcher;

    public TelegramWebhookController(UpdateDispatcherService dispatcher) {
        this.dispatcher = dispatcher;
    }

    @PostMapping
    public void onUpdate(@RequestHeader("X-Telegram-Bot-Api-Secret-Token") String secret, @RequestBody Update update) {
        if (!secret.equals("123456789")) return;

        dispatcher.dispatch(update);
    }

}
