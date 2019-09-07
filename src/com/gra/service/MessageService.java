package com.gra.service;

import java.util.List;

import com.gra.entity.Message;

public interface MessageService {
	public List<Message> getMessageByFromId(int from);
	public List<Message> getMessageByToId(int to);
	public List<Message> getMessageUnReceive(int to);
	public List<Message> getMessageByType(int type);
	public void addMessage(Message message);
	public boolean deleteMessage(Message message);
	public boolean updateMessage(Message message);
}
