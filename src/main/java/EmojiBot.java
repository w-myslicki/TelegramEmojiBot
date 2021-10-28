import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EmojiBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "javaEmoji_bot";
    }

    @Override
    public String getBotToken() {
        return "2099218239:AAFqFPmAfUjNxb_aSlqSkBXD2GG1Ys7scY8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && (update.getMessage().hasText() || update.getMessage().hasSticker())) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(EmojiParser.parseToUnicode(getEmoji()));

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getEmoji() {
        Random random = new Random();

        List<Emoji> emojiList = new ArrayList<>(EmojiManager.getAll());
        Emoji emojiObject = emojiList.get(random.nextInt(emojiList.size()));

        return emojiObject.getUnicode();
    }
}
