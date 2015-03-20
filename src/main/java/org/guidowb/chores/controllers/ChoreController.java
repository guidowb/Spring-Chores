package org.guidowb.chores.controllers;

import org.guidowb.chores.domain.Chore;
import org.guidowb.chores.repositories.ChoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chores")
public class ChoreController {

    private static final Logger logger = LoggerFactory.getLogger(ChoreController.class);
	private ChoreRepository repository;

	@Autowired
	public ChoreController(ChoreRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Chore> chores() {
		return repository.findAll();
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public Chore get(@PathVariable long id) {
		logger.info("Getting chore " + Long.toString(id));
		return repository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody Chore chore) {
		logger.info("Creating new chore " + chore.getName());
		repository.save(chore);
	}

	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Chore chore) {
		logger.info("Updating chore " + chore.getName());
		repository.save(chore);
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		logger.info("Deleting chore " + Long.toString(id));
		repository.delete(id);
	}
}
