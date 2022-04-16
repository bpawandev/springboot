package com.baikati;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.baikati.domain.Image;
import com.baikati.service.ImageService;

@Component
@Order(2)
public class DataLoader implements CommandLineRunner {
	private final Logger log = org.slf4j.LoggerFactory.getLogger(DataLoader.class);
	
	@Autowired
	private ImageService imageService;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("Loading Data...");
		imageService.save(new Image("Image1"));
		imageService.save(new Image("Image2"));
		imageService.save(new Image("Image3"));
		
		/*
		 * List<Image> images = imageService.findAll();
		 * images.forEach(System.out::println);
		 */

	}

}
