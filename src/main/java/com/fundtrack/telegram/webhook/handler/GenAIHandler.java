package com.fundtrack.telegram.webhook.handler;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class GenAIHandler {

    private final String apiKey = "AIzaSyAOa3qizkT94iMhGC7hkjl3iAI6WWlRhp4";
    private final String aiAgent = "gemini-2.5-flash";

    private final String SYSTEM_RULE = "You are the assistant for 'FundTrack' telegram bot project. " +
            "Only answer question/text by friendly message and introduce user when needed. " +
            "Be friendly to user and short answer but meaningful. " +
            "Your response must be in MarkdownV2 that supported by Telegram API. " +
            "You shall prevent any markdown as point that may make wrong result sent to telegram, point should be used - instead of *" +
            "Any question/talk outside the topic, you shall not response, by response nice message instead of reply to the talk/question.";

    private final Content systemRule = Content.fromParts(Part.fromText(SYSTEM_RULE));

    public String responseMessage(Update update) {

        if (update.getMessage().getText() == null) return null;

        return this.getAIResponse(update.getMessage().getText());
    }

    private String getAIResponse(String message) {

        try {

            Client client = Client.builder()
                    .apiKey(apiKey)
                    .build();

            GenerateContentConfig config = GenerateContentConfig.builder()
                    .systemInstruction(systemRule)
                    // .temperature(0.7f) // Only if you want desired creativity
                    .build();

            GenerateContentResponse response = client.models.generateContent(
                    aiAgent,
                    message,
                    config
            );

            System.out.println(response.text());
            return response.text();

        }
        catch (Exception e) {
            System.out.println("An Error Occurred at GenAIHandler.getAIResponse: " + e.getMessage());
            return null;
        }

    }

}
