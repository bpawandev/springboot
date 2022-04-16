package com.baikati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baikati.domain.Image;
import com.baikati.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void save(Image image) {
		imageRepository.save(image);
	}
	
	public List<Image> findAll() {
		return imageRepository.findAll();
	}
}
