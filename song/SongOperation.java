package com.jspider.song;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class SongOperation {
	static List<Song> albumList =new ArrayList<Song>();
	static Scanner sc = new Scanner(System.in);
	static int choice;
	

	public void playSong() {
		System.out.println("1.Play all songs");
		System.out.println("2.Choose song");
		System.out.println("3.Play Random");
		System.out.println("4.Go back");
		System.out.println("--------------------------------");
		
		SongOperation songOperation=new SongOperation();

		System.out.println("choose option : ");
		int choice = sc.nextInt();

		switch (choice) {
		
		case 1:
			System.out.println("All songs are playing");
			System.out.println(albumList);
			System.out.println("--------------------------------");
			
			break;
		case 2:
			
			System.out.println("Select one song");
			System.out.println("--------------------------------");
			
			break;
		case 3:
			System.out.println("Songs playing randomly now");
			System.out.println("--------------------------------");

			break;
		case 4: 
			songOperation.goBack();
			System.out.println("--------------------------------");
			break;
		

		default:
			System.out.println("Invalid input");
			System.out.println("--------------------------------");
			break;
		}
	}

	public void addRemove() {
		System.out.println("1.Add song");
		System.out.println("2.Remove Song");
		System.out.println("3.All songs");
		System.out.println("4.Go back");
		System.out.println("choose option : ");
		System.out.println("--------------------------------");
		SongOperation operation = new SongOperation();
		 choice = sc.nextInt();

		switch (choice) {
		case 1:
			operation.addsong();
			System.out.println("1.Go Back");
			 choice = sc.nextInt();
			switch (choice) {
			case 1:
				operation.goBack();
				System.out.println("--------------------------------");
				break;
			
			default:
			System.out.println("Invalid input");
			break;
			}
			
			case 2:
				operation.removesong();
				System.out.println("song is Removed");
				System.out.println(albumList);
				System.out.println("1.Go Back");
				System.out.println("--------------------------------");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					operation.goBack();
					break;
				
				default:
				System.out.println("Invalid input");
				break;
				}
				break;
			case 3:
				System.out.println(albumList);	
			case 4:
				operation.goBack();
				break;
			
			default:
				System.out.println("Invalid input");
			}

		}
	private void removesong() {
		System.out.println("put song id");
		  choice= sc.nextInt();
		 
		albumList.remove(choice-1);
//		if (albumList.get(0) == name) {
//			albumList.remove(albumList.get(1));
//			System.out.println("song is removed");
//		}
//		else {
//			System.out.println("song is not found");
//		}
			
		}

	public void addsong() {
		Song song = new Song();
		System.out.println("put song id");
		int songid = sc.nextInt();
		song.setId(songid);

		System.out.println("put song name");
		String songName = sc.next();
		song.setName(songName);

		System.out.println("put album name");
		String album = sc.next();
		song.setAlbum(album);

		System.out.println("put singer name");
		String singer = sc.next();
		song.setSinger(singer);

		albumList.add(song);
		
		// song.add(new ArrayList ());
		System.out.println(songName + " successfully added to the list");
		System.out.println("--------------------------------");
		
		for(Song s:albumList) {
			System.out.println(s);
		}
	}

	public void goBack() {
		MusicPlayer.test();
	}

	public void remove() {
		MusicPlayer.test();
	}

	public void updateSong() {
		System.out.println("1.Name");
		System.out.println("2.singer");
		System.out.println("3.Album");
		System.out.println("4.Go back");
		System.out.println("--------------------------------");
		SongOperation updateop = new SongOperation();
		 choice = sc.nextInt();
		switch (choice) {
		case 1:
	//		System.out.println(songName);

		case 4:
			updateop.goBack();
			break;
		default:
			System.out.println("Invalid input");

		}
	}

	public void exit() {
		System.out.println("Exit Appliation");

	}
}
