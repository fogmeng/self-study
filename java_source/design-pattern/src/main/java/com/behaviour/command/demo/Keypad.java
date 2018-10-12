package com.behaviour.command.demo;

public class Keypad {
	private Command playCommand;
	private Command rewindCommand;
	private Command stopCommand;

	public void setPlayCommand(Command playCommand) {
		this.playCommand = playCommand;
	}

	public void setRewindCommand(Command rewindCommand) {
		this.rewindCommand = rewindCommand;
	}

	public void setStopCommand(Command stopCommand) {
		this.stopCommand = stopCommand;
	}

	/**
	 * ִ�в��ŷ���
	 */
	public void play() {
		playCommand.execute();
	}

	/**
	 * ִ�е�������
	 */
	public void rewind() {
		rewindCommand.execute();
	}

	/**
	 * ִ�в��ŷ���
	 */
	public void stop() {
		stopCommand.execute();
	}
}