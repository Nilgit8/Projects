package com.jspider.musicplayerhibernate.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jspider.musicplayerhibernate.songoperation.SongOperations;
import com.jspider.musicplayerhibernate.songs.Songs;

public class MusicPlayer {

	private JFrame frm1;
	private JTextField tfSongName;
	private JTextField tfSingerName;
	private JLabel lblMovieName;
	private JTextField tfMovieName;
	private JLabel lblSongDuration;
	private JTextField tfSongDuration;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnList;
	private JLabel lblSearchSong;
	private JTextField tfSearchSong;
	private JLabel lblNewLabel;
	private JButton btnReset;
	private JButton btnStop;

	private Clip clip;
	private JButton btnPause;
	private boolean startedPlaying=true;
	private boolean play=true;
	private long clipTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicPlayer window = new MusicPlayer();
					window.frm1.setVisible(true);
					window.frm1.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MusicPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm1 = new JFrame();
		frm1.getContentPane().setBackground(new Color(192, 192, 192));
		frm1.getContentPane().setForeground(new Color(128, 0, 255));
		frm1.getContentPane().setFont(new Font("Stencil", Font.PLAIN, 20));
		frm1.setFont(new Font("Elephant", Font.BOLD, 20));
		frm1.setType(Type.UTILITY);
		frm1.setForeground(new Color(192, 192, 192));
		frm1.setTitle("Music Player");
		frm1.setBounds(0, -16, 700, 580);
		frm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm1.getContentPane().setLayout(null);

		JLabel lbSingerName = new JLabel("Singer Name ");
		lbSingerName.setBounds(26, 187, 120, 50);
		lbSingerName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(lbSingerName);

		tfSongName = new JTextField();
		tfSongName.setBounds(166, 117, 500, 50);
		tfSongName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(tfSongName);
		tfSongName.setColumns(10);

		JLabel lbSongName = new JLabel("Song Name ");
		lbSongName.setBounds(26, 117, 113, 50);
		lbSongName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(lbSongName);

		tfSingerName = new JTextField();
		tfSingerName.setBounds(166, 189, 500, 50);
		tfSingerName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSingerName.setColumns(10);
		frm1.getContentPane().add(tfSingerName);

		lblMovieName = new JLabel("Movie Name ");
		lblMovieName.setBounds(26, 257, 120, 50);
		lblMovieName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(lblMovieName);

		tfMovieName = new JTextField();
		tfMovieName.setBounds(166, 257, 500, 50);
		tfMovieName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfMovieName.setColumns(10);
		frm1.getContentPane().add(tfMovieName);

		lblSongDuration = new JLabel("Song Duration");
		lblSongDuration.setBounds(26, 327, 120, 50);
		lblSongDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(lblSongDuration);

		tfSongDuration = new JTextField();
		tfSongDuration.setBounds(166, 327, 500, 50);
		tfSongDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSongDuration.setColumns(10);
		frm1.getContentPane().add(tfSongDuration);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfSongName.getText().equals("") || tfSingerName.getText().equals("")
						|| tfMovieName.getText().equals("") || tfSongDuration.getText().equals("")) {
					JOptionPane.showMessageDialog(frm1, "Please Enter All Data !");
				} else {
					SongOperations.addSong(tfSongName.getText(), tfSingerName.getText(), tfMovieName.getText(),
							Double.parseDouble(tfSongDuration.getText()));
					if (tfSongName.getText() != null) {
						JOptionPane.showMessageDialog(frm1, "Song Added Succesfully !");

					}
				}
			}
		});
		btnAdd.setBounds(57, 467, 100, 50);
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBackground(new Color(0, 255, 0));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frm1.getContentPane().add(btnAdd);

		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfSearchSong.getText().equals("")) {
					JOptionPane.showMessageDialog(frm1, "Please Enter Song ID Details");
				} else {
					if (SongOperations.songPresent(Integer.parseInt(tfSearchSong.getText()))) {
						Songs song=SongOperations.chooseToPlaySong(Integer.parseInt(tfSearchSong.getText()));
						tfSongName.setText(song.getSongName());
						tfSingerName.setText(song.getSingerName());
						tfMovieName.setText(song.getMovieName());
						tfSongDuration.setText(song.getSongDuration() + "");
						int showConfirmDialog = JOptionPane.showConfirmDialog(frm1, "Yes To Confirm");
						if (showConfirmDialog==0) {
							SongOperations.removeSong(Integer.parseInt(tfSearchSong.getText()));
							JOptionPane.showMessageDialog(frm1, "Song Deleted Sucessfully !");
						}
						else if(showConfirmDialog==1) {
							JOptionPane.showMessageDialog(frm1, "Song Not Removed!");
						}
					} else {
						JOptionPane.showMessageDialog(frm1, "Song Not Found !");
					}
					
				}
			}
		});
		btnDelete.setBounds(214, 467, 100, 50);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBackground(new Color(255, 0, 0));
		frm1.getContentPane().add(btnDelete);

		btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfSearchSong.getText().equals("")) {
					JOptionPane.showMessageDialog(frm1, "Please Add Song Id !");
				} else {
					int songId = Integer.parseInt(tfSearchSong.getText());
					Songs song = SongOperations.chooseToPlaySong(songId);
					if (song == null) {
						JOptionPane.showMessageDialog(frm1, "Song Not Found!");
					} else {
						tfSongName.setText(song.getSongName());
						tfSingerName.setText(song.getSingerName());
						tfMovieName.setText(song.getMovieName());
						tfSongDuration.setText(song.getSongDuration() + "");
					}
				}
			}
		});
		btnSearch.setBounds(444, 21, 100, 50);
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBackground(SystemColor.controlDkShadow);
		frm1.getContentPane().add(btnSearch);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfSearchSong.getText().equals("")) {
					JOptionPane.showMessageDialog(frm1, "Please Add Proper Details");
				} else {
					if (SongOperations.songPresent(Integer.parseInt(tfSearchSong.getText())) ) {
						if (tfSongName.getText().equals("")) {
							JOptionPane.showMessageDialog(frm1, "Please Update Song Details !");
						}else {
							SongOperations.updateSong(Integer.parseInt(tfSearchSong.getText()), tfSongName.getText(),
									tfSingerName.getText(), tfMovieName.getText(),
									Double.parseDouble(tfSongDuration.getText()));
							JOptionPane.showMessageDialog(frm1, "Song Updated Sucessfully !");
						}
					} else {
						JOptionPane.showMessageDialog(frm1, "Song Not Found !");
					}
					
				}
			}
		});
		btnUpdate.setBounds(371, 467, 100, 50);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBackground(new Color(0, 191, 255));
		frm1.getContentPane().add(btnUpdate);

		btnList = new JButton("LIST");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				listFrame.main(null);
				JFrame frame=new JFrame("Song List");
				frame.setLocationRelativeTo(btnReset);
				frame.toFront();
				String allString=SongOperations.songList();
				TextArea textArea = new TextArea(10,20);
				JScrollPane scroll=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				textArea.setText(allString);
				frame.setBounds(1120, 150, 300, 500);
				frame.getContentPane().add(scroll);
				frame.setSize(300,500);
				frame.setVisible(true);
				textArea.setEditable(false);
				textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
			}
		});
		btnList.setBounds(528, 467, 100, 50);
		btnList.setForeground(Color.BLACK);
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnList.setBackground(new Color(255, 165, 0));
		frm1.getContentPane().add(btnList);

		lblSearchSong = new JLabel("Search Song By ID");
		lblSearchSong.setBounds(26, 20, 167, 50);
		lblSearchSong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frm1.getContentPane().add(lblSearchSong);

		tfSearchSong = new JTextField();
		tfSearchSong.setBounds(203, 20, 220, 50);
		tfSearchSong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSearchSong.setColumns(10);
		frm1.getContentPane().add(tfSearchSong);

		lblNewLabel = new JLabel("ADD SONGS IN LIST");
		lblNewLabel.setFont(new Font("STXinwei", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 78, 640, 29);
		frm1.getContentPane().add(lblNewLabel);

		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfSearchSong.setText("");
				tfSongName.setText("");
				tfSingerName.setText("");
				tfMovieName.setText("");
				tfSongDuration.setText("");
			}
		});
		btnReset.setForeground(Color.BLACK);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReset.setBackground(SystemColor.controlDkShadow);
		btnReset.setBounds(566, 21, 100, 50);
		frm1.getContentPane().add(btnReset);
		
		JButton btnload = new JButton("PLAY ");
		btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (play) {
					if (tfSearchSong.getText().equals("")) {
						JOptionPane.showMessageDialog(frm1, "Please Add Proper Details");
					} else {
						if (SongOperations.songPresent(Integer.parseInt(tfSearchSong.getText()))) {
							if (tfSearchSong.getText().equalsIgnoreCase("8")) {

								String filePath="C://Users//Admin//Downloads//Tum Hi Ho.wav";
								try {
									java.io.File file=new java.io.File(filePath);
									if (file.exists()) {
										AudioInputStream aStream=AudioSystem.getAudioInputStream(file);
										clip = AudioSystem.getClip();
										clip.open(aStream);
										clip.start();
										Songs song = SongOperations.chooseToPlaySong(Integer.parseInt(tfSearchSong.getText()));
										tfSongName.setText(song.getSongName());
										tfSingerName.setText(song.getSingerName());
										tfMovieName.setText(song.getMovieName());
										tfSongDuration.setText(song.getSongDuration() + "");
										play=!play;
									}else {
										
										JOptionPane.showMessageDialog(frm1, "Song Not Found!");
									}
								} catch (Exception e2) {
									// TODO: handle exception
								}
							
							} else {
								Songs song = SongOperations.chooseToPlaySong(Integer.parseInt(tfSearchSong.getText()));
								tfSongName.setText(song.getSongName());
								tfSingerName.setText(song.getSingerName());
								tfMovieName.setText(song.getMovieName());
								tfSongDuration.setText(song.getSongDuration() + "");
								JOptionPane.showMessageDialog(frm1, "Song Audio File Not Found!");
							}
						}else {
							JOptionPane.showMessageDialog(frm1, "Song Not Found !");
						}
					}
				}
			}
		});
		btnload.setForeground(Color.BLACK);
		btnload.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnload.setBackground(SystemColor.activeCaption);
		btnload.setBounds(51, 394, 160, 50);
		frm1.getContentPane().add(btnload);
		
		btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						clip.close();
						tfSearchSong.setText("");
						tfSongName.setText("");
						tfSingerName.setText("");
						tfMovieName.setText("");
						tfSongDuration.setText("");
						btnPause.setText("PAUSE");
						play=!play;
			}
		});
		btnStop.setForeground(Color.BLACK);
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStop.setBackground(new Color(255, 99, 71));
		btnStop.setBounds(473, 394, 160, 50);
		frm1.getContentPane().add(btnStop);
		
		btnPause = new JButton("PAUSE");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (startedPlaying) {
					clipTime=clip.getMicrosecondPosition();
					clip.stop();
					btnPause.setText("RESUME");
				}else {
					clip.setMicrosecondPosition(clipTime);
					clip.start();
					btnPause.setText("PAUSE");
				}
				startedPlaying=!startedPlaying;
				
			}
			
		});
		btnPause.setForeground(Color.BLACK);
		btnPause.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPause.setBackground(SystemColor.activeCaption);
		btnPause.setBounds(262, 394, 160, 50);
		frm1.getContentPane().add(btnPause);
	}
}