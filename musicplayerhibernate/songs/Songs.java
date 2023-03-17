package com.jspider.musicplayerhibernate.songs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Songs {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int songId;
	private String songName;
	private String singerName;
	private String movieName;
	private double songDuration;
	@Override
	public String toString() {
		return "        SONG ID= "+this.songId
				+"\n  Song Name= "+this.songName
				+"\n  Singer Name= "+this.singerName
				+"\n  Movie Name= "+this.movieName
				+"\n  Song Duration= "+this.songDuration
				+"\n";
		
	}
}


 