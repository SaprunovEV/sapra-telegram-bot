package by.sapra.telegrambot.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @Description TODO
 * @Author Sapra
 * @Date 24.05.2022 17:15
 */
@Component
public class SapraTestgramBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.name}")
    private String botName;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text(update.getMessage().getText())
                    .build();

            execute(sendMessage);
        }
    }
}
