package com.baikati;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.baikati.domain.Image;
import com.baikati.service.ImageService;

@Component
public class ImageDataLoader implements CommandLineRunner {

	@Autowired
	ImageService imageService;

	@Override
	public void run(String... args) throws Exception {
		imageService.save(new Image("Image4"));
		imageService.save(new Image("Image5"));
		imageService.save(new Image("Image6"));
		
		List<Image> images = imageService.findAll();
		images.forEach(System.out::println);

	}
}
