package com.example.TopicProject.services;

import java.util.List;
import java.util.Optional;

import com.example.TopicProject.entities.Topic;
import com.example.TopicProject.exception.custom.BusinessException;

public interface TopicService {
	List<Topic> getAllTopics() throws BusinessException;

	Topic getTopic(String id) throws BusinessException;

	Topic getTopicByName(String name);

	Topic addTopic(Topic topic) throws BusinessException;

	Topic updateTopic(String id, Topic topic) throws BusinessException;

	void deleteTopic(String id) throws BusinessException;
}
