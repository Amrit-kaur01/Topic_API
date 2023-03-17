package com.example.TopicProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TopicProject.entities.Topic;
import com.example.TopicProject.exception.custom.BusinessException;
import com.example.TopicProject.services.TopicService;
import com.example.TopicProject.services.TopicServiceImpl;

@RestController
public class TopicController {

	@Autowired // used to declare a dependency that spring needs to inject at runtime
	private TopicService topicService;

	@GetMapping("/topics")
	public ResponseEntity<List<Topic>> getAllTopics() { // whatever this method returns will
														// automatically be converted to
														// JSON and sent
		// back as the HTTPResponse since this is a RestController
		List<Topic> list = topicService.getAllTopics();
		return new ResponseEntity<>(list, HttpStatus.OK);
		/*
		 * the generated JSON will have key names corresponding to property names of the
		 * Topic class The JSON values are the values of those properties
		 */
	}

	@GetMapping("/topics/{id}") // the id inside the curly brackets indicates that it is a variable token and it
								// can take any value and that value will be passed to the method
	public ResponseEntity<Topic> getTopic(@PathVariable String id) {
		Topic topic = topicService.getTopic(id);
		return new ResponseEntity<>(topic, HttpStatus.OK);
	}

	@GetMapping("/topic")
	public ResponseEntity<Topic> getTopicByName(@RequestParam("name") String name) {
		Topic topic = topicService.getTopicByName(name);
		return new ResponseEntity<>(topic, HttpStatus.OK);

	}

	@PostMapping("/topics")
	public ResponseEntity<Topic> addTopic(@RequestBody Topic topic) // @RequestBody converts or maps the HTTPRequest
																	// body into a Topic
																	// instance, enabling automatic deserialization
	{
		Topic topicAdded = topicService.addTopic(topic);
		return new ResponseEntity<>(topicAdded, HttpStatus.CREATED);

	}

	@PutMapping("/topics/{id}")
	public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		Topic topicUpdated = topicService.updateTopic(id, topic);
		return new ResponseEntity<>(topicUpdated, HttpStatus.OK);

	}

	@DeleteMapping("/topics/{id}")
	public ResponseEntity<Object> deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
		return new ResponseEntity<>("Topic with id " + id + " deleted successfully", HttpStatus.ACCEPTED);
	}
}
