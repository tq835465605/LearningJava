package com.foxhis.spring.di;

import org.springframework.stereotype.Component;

@Component
public class Album {
	
	private String artister;
	private String album;
	
	public Album(String artist,String album) {
		// TODO Auto-generated constructor stub
		this.album = album;
		this.artister = artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	public String getArtister()
	{
		return this.artister;
	}

}
