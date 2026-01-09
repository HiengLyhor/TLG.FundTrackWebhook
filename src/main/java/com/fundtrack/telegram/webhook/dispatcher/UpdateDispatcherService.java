package com.fundtrack.telegram.webhook.dispatcher;

import com.fundtrack.telegram.webhook.handler.IUpdateHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class UpdateDispatcherService {

    private final List<IUpdateHandler>  handlers;

    public UpdateDispatcherService(List<IUpdateHandler> handlers) {
        this.handlers = handlers;
    }

    // All implements of IUpdateHandler will be here
    // We check each supports implementation in order to know which class is the match one for the case we needed
    public void dispatch(Update update) {
        handlers.stream()
                .filter(h -> h.supports(update))
                .findFirst()
                .ifPresent(h -> h.handle(update));
    }

}
