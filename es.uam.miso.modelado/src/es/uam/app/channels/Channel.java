package es.uam.app.channels;

import es.uam.app.message.ReceivedMessage;
import es.uam.app.message.SendMessageExc;

public abstract class Channel extends Thread {

	private String channelName;
	private Pipe pipe;


	public Channel(String name, Pipe p) {
		this.channelName = name;
		this.pipe=p;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setchannelName(String name) {
		this.channelName = name;
	}

	public void write(ReceivedMessage msg) {
		pipe.write(msg);
	}

	public abstract void answerMessage(ReceivedMessage rMessage, SendMessageExc sMessage);
	public abstract void updateProject(SendMessageExc sMessage);
	public abstract void run();
}