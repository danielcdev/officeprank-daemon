package com.danielcotter.officeprank.daemon.util;

import java.io.File;
import java.net.URL;

public class ScareUtil {

	private MouseUtil mouseUtil = new MouseUtil();
	private ScreenUtil screenUtil = new ScreenUtil();
	private SoundUtil soundUtil = new SoundUtil();
	private URL image = ScareUtil.class.getResource("/image.bmp");
	private URL sound = ScareUtil.class.getResource("/sound.wav");

	public Thread arm() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					File currentDesktop = new File(
							System.getProperty("user.home") + File.separator
									+ "screengrab.bmp");

					screenUtil.screenshot(currentDesktop);

					while (true) {
						screenUtil.showImage(currentDesktop.toURI().toURL());

						mouseUtil.wakeOnMove();

						Thread t = soundUtil.playClip(sound);
						screenUtil.flashImage(image, currentDesktop.toURI()
								.toURL());
						screenUtil.showImage(image);
						t.join();

						screenUtil.showImage(currentDesktop.toURI().toURL());
					}
				} catch (Exception e) {
					screenUtil.shutdown();
					return;
				}
			}
		});

		t.start();
		return t;
	}
}