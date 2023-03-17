package com.example.TopicProject.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TopicProject.entities.Topic;
import com.example.TopicProject.exception.custom.BusinessException;
import com.example.TopicProject.repositories.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService { // this class will provide business service to the controller.
														// Then controller
	// may give the response to the url request made by the user

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);

		return topics;
	}

	@Override
	public Topic getTopic(String id) {
		return topicRepository.findById(id)
				.orElseThrow(() -> new BusinessException("NOT_FOUND", "Topic with id " + id + " doesn't exists"));

	}

	@Override
	public Topic addTopic(Topic topic) throws BusinessException {
		if (topic.getName().isEmpty())
			throw new BusinessException("BAD_REQUEST", "Name cannot be empty");
		else
			return topicRepository.save(topic);

	}

	@Override
	public Topic updateTopic(String id, Topic newTopic) throws BusinessException {
		/*
		 * save() method can do both add and update an instance that's why using same
		 * method in both .this is because the sent instance has the complete
		 * information or data hence the repository will check if there is already a row
		 * with same id. If it is so, then it will update the row. Otherwise add a new
		 * row i.e insert
		 */
		if (!topicRepository.existsById(id))
			throw new BusinessException("CONFLICT", "Topic with id " + id + " doesn't exists. You can add it.");
		else if (newTopic.getName().isEmpty())
			throw new BusinessException("BAD_REQUEST", "Name cannot be empty");
		else {
			return topicRepository.findById(id).map(topic -> {
				topic.setName(newTopic.getName());
				topic.setDescription(newTopic.getDescription());
				return topicRepository.save(topic);
			}).orElseGet(() -> {
				newTopic.setId(Integer.parseInt(id));
				return topicRepository.save(newTopic);
			});
		}

	}

	@Override
	public void deleteTopic(String id) throws BusinessException {
		if (!topicRepository.existsById(id))
			throw new BusinessException("NOT_FOUND", "Topic with id " + id + " doesn't exists");
		else
			topicRepository.deleteById(id);
	}

	@Override
	public Topic getTopicByName(String name) throws BusinessException {

		return topicRepository.findByName(name)
				.orElseThrow(() -> new BusinessException("NOT_FOUND", "Topic with name " + name + " doesn't exists"));

	}
}
