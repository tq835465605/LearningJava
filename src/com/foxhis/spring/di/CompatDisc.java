package com.foxhis.spring.di;

import org.springframework.beans.factory.annotation.Autowired;

public class CompatDisc implements Player{

	@Autowired
	private Album album;
	

	/*public  CompatDisc(Album album) {
		// TODO Auto-generated constructor stub
		this.album = album;

	}*/
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("play" + album.getArtister() +" "+ album.getAlbum());
	}

}
