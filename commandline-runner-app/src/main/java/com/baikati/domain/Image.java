package com.baikati.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
	@Id
	@GeneratedValue
	private Long Id;
	private String name;

	public Image(String name) {
		this.name = name;
	}

	public Image() {

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Image [Id=" + Id + ", name=" + name + "]";
	}

}
