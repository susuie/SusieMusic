package com.gra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gra.dao.MessageDAO;
import com.gra.entity.Message;
import com.gra.service.MessageService;
@Transactional
@Service
public class MessageServiceImplement implements MessageService {

	@Autowired private MessageDAO messageDao;
	
	@Override
	public List<Message> getMessageByFromId(int from) {
		return messageDao.getMessageByFromId(from);
	}

	@Override
	public List<Message> getMessageByToId(int to) {
		return messageDao.getMessageByToId(to);
	}
	
	@Override
	public List<Message> getMessageUnReceive(int to) {
		return (List<Message>)messageDao.getMessageUnReceive(to);
	}

	@Override
	public List<Message> getMessageByType(int type) {
		return messageDao.getMessageByType(type);
	}

	@Override
	public void addMessage(Message message) {
		messageDao.addMessage(message);

	}

	@Override
	public boolean deleteMessage(Message message) {
		return messageDao.deleteMessage(message);
	}

	@Override
	public boolean updateMessage(Message message) {
		return messageDao.updateMessage(message);
	}

}
