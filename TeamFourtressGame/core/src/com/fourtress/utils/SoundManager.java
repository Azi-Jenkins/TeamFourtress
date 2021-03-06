package com.fourtress.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static Music music;
	private static Sound sfx;
	private static float musicVolume = 0.5f;
	private static float sfxVolume = 0.5f;
	private static boolean musicStatus = true;
	private static boolean sfxStatus = true;

	private static String musicTrack = "";

	// Audio for Music
	public static void playMusic(MyAssetManager assMan) {
		// Check to see if Music has been turned off by the player
		if (musicStatus == true) {
			if (music != null) {
				music.stop();
			}
			music = assMan.getMusic();
			music.setLooping(true);
			music.setVolume(musicVolume);
			music.play();
		}
	}

	// Audio for Sound Effects
	public static void playSFX(Sfx sound, MyAssetManager assMan) {
		// Check to see if SFX has been turned off by the player
		if (sfxStatus == true) {
			sfx = assMan.getSfx(sound);
			sfx.play(sfxVolume);
		}
	}

	// Set the Music volume value
	public static void setMusicVolume(float volume) {
		musicVolume = volume;
		if (music != null) {
			music.setVolume(musicVolume);
		}
	}

	// Set the SFX volume value
	public static void setSFXVolume(float volume) {
		sfxVolume = volume;
		if (sfx != null) {
			sfx.setVolume(0, sfxVolume);
		}
	}

	// Set whether Music will be on or off
	public static void toggleMusic(boolean status) {
		musicStatus = status;

		if (music != null) {
			if (musicStatus == true) {
				music.setLooping(true);
				music.setVolume(musicVolume);
				music.play();
			} else {
				music.stop();
			}
		}
	}

	// Set whether SFX will be on or off
	public static void toggleSFX(boolean status) {
		sfxStatus = status;

		if (sfx != null) {
			if (sfxStatus == true) {
				sfx.setVolume(0, sfxVolume);
				sfx.play();
			} else {
				sfx.stop();
			}
		}
	}

	// Store the filePath of the current music track playing
	private static void setMusic(String filePath) {
		musicTrack = filePath;
	}

	public static void dispose() {
		if (music != null) {
			music.stop();
			music.dispose();
		}

		if (sfx != null) {
			sfx.stop();
			sfx.dispose();
		}
	}
}
