package es.uam.app.channels.telegram;

import org.telegram.telegrambots.api.objects.Update;

import es.uam.app.main.Main;
import es.uam.app.message.SendMessageExc;

public class Redo extends TelegramCommand {

	public Redo(TelegramControl tChannel) {
		super(tChannel);
	}

	@Override
	public String getCommand() {
		return "redo";
	}

	@Override
	public String getDescription() {
		return "redo the last undo";
	}

	@Override
	public void commandAction(Update update) {
		this.removerUserTalk(update.getMessage().getChatId(), update.getMessage().getFrom());

		String project = this.getProject(update.getMessage().getChatId());
		if (project == null || project.equals("")) {
			SendMessageExc sent = new SendMessageExc(STANDARD_ERROR_MSG);
			tChannel.sendMessage(-1, update.getMessage().getChatId(), sent);
		} else {
			tChannel.write(update, Main.MainCommandEnum.REDO.getName(), project, "");
		}

	}

}
