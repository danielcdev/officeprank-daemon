package com.danielcotter.officeprank.daemon.util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundUtil {

	public Thread playClip(URL url) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream audioInputStream = AudioSystem
							.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
					Thread.sleep(clip.getMicrosecondLength() / 1000);
					clip.stop();
				} catch (Exception e) {
					return;
				}
			}
		});

		t.start();
		return t;
	}
}
