package com.simpleJwt.server.SimpleJwtServer.reposMongoDB.data;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ravin
 *
 */
@Document
public class Books {

	@Id
	private String _id;
	
	private String name;
	
	private int age;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
