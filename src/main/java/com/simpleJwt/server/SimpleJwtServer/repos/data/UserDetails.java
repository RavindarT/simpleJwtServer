package com.simpleJwt.server.SimpleJwtServer.repos.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERDETAILS")
public class UserDetails {
	
	@Id
	@Column(name = "username")
	String userName;
	
	@Column(name = "password")
	String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	
}
