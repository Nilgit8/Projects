package com.jspider.musicplayerhibernate.songoperation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspider.musicplayerhibernate.songs.Songs;

public class SongOperations {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;

//	// Open connection common for all operations
	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("musicplayer");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

	}

	// close connection common for operations
	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}

	// Add song
	public static void addSong(String songName, String singerName, String movieName, double songDuration) {

		try {
			openConnection();
			transaction.begin();
			Songs songs = new Songs();
			songs.setSongName(songName.toLowerCase());

			songs.setSingerName(singerName.toLowerCase());

			songs.setMovieName(movieName.toLowerCase());

			songs.setSongDuration(songDuration);

			manager.persist(songs);
			transaction.commit();
			if (!transaction.isActive()) {

			}
		} finally {
			closeConnection();
		}
	}

	public static void removeSong(int songId) {
		try {
			openConnection();
			transaction.begin();
			Songs songs = manager.find(Songs.class, songId);
			manager.remove(songs);
			transaction.commit();

		} finally {
			closeConnection();
		}
	}

	public static Songs chooseToPlaySong(int songId) {
		try {
			openConnection();
			transaction.begin();
			Songs songs = manager.find(Songs.class, songId);

			transaction.commit();

			return songs;
		} finally {
			closeConnection();
		}
	}

	public static String songList() {
		try {
			openConnection();
			transaction.begin();
			String queString = "from Songs";
			Query query = manager.createQuery(queString);
			List<?> songs = query.getResultList();
			String songListString = "";
			for (Object songs2 : songs) {
				songListString = songListString + "\n" + songs2 + " ";
			}
			transaction.commit();
			return songListString;
		} finally {
			closeConnection();
		}
	}

	public static boolean songPresent(int songId) {
		try {
			openConnection();
			transaction.begin();
			Songs song = manager.find(Songs.class, songId);
			transaction.commit();
			if (song == null) {
				return false;
			} else {
				return true;
			}
		} finally {
			closeConnection();
		}
	}

	public static void updateSong(int songId, String newSongName, String newSingerName, String newMovieName,
			double newSongDuration) {
		try {
			openConnection();
			transaction.begin();
			Songs song = manager.find(Songs.class, songId);
			song.setSongName(newSongName);
			song.setSingerName(newSingerName);
			song.setMovieName(newMovieName);
			song.setSongDuration(newSongDuration);
			manager.persist(song);

			transaction.commit();
		} finally {
			closeConnection();
		}
	}

}