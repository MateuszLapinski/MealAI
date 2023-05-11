package org.example;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;

public class chatGPTHelper {
    OpenAiService service;
    public chatGPTHelper() {
        service = new OpenAiService("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", Duration.ofSeconds(30));
    }


    public String getBreakfastIdea(List<String> products) {
        String allProducts = String.join(",", products);
        String question= "I have in the fridge" + allProducts + ". what can I eat for breakfast? Give me three ideas.Give me a information about calories for every meal, please";

        return askChatGPT(question);

    }

    public String getDinnerIdea(List<String> products) {
        String allProducts = String.join(",", products);
        String question= "I have in the fridge " + allProducts + ". what can I eat for dinner? Give me three ideas for meal, each containing 500 calories. Give me a precise information about calories for every meal, please";

        return askChatGPT(question);

    }
    private String askChatGPT (String question) {

    //Indication of the user and chatgpt model

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();

        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();

        StringBuilder stringBuilder = new StringBuilder();

        //Selecting an answer and filter to a clean answer
        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);


        return stringBuilder.toString();

    }

    public String getHealthyProductsRecommendation(List<String> products) {
        String allProducts = String.join(",", products);
        String question= "Mam w lodówce" + allProducts + "Jakie inne zdrowe produkty mogę kupić, ogranicz się do 10 produktów. Podaj też koło nazwy produktu jego kaloryczność ";

        return askChatGPT(question);
    }
}
