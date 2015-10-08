package com.danielcotter.officeprank.daemon.util;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ScreenUtil {

	private GraphicsEnvironment environment = GraphicsEnvironment
			.getLocalGraphicsEnvironment();
	private GraphicsDevice device = environment.getDefaultScreenDevice();
	private Frame frame;

	public void screenshot(File file) throws AWTException, IOException,
			URISyntaxException {
		ImageIO.write(new Robot().createScreenCapture(new Rectangle(Toolkit
				.getDefaultToolkit().getScreenSize())), "bmp", file);
	}

	public void showImage(URL url) throws MalformedURLException, IOException {
		frame.removeAll();

		frame.add(new Component() {
			private static final long serialVersionUID = 1L;
			BufferedImage img = ImageIO.read(url);

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		});

		frame.validate();
		frame.setVisible(true);
		device.setFullScreenWindow(frame);
	}

	public void flashImage(URL main, URL toFlash) throws InterruptedException,
			MalformedURLException, IOException {
		for (int i = 1; i <= 5; i++) {
			Thread.sleep(20);
			showImage(toFlash);
			Thread.sleep(40);
			showImage(main);
		}
	}
	
	public void shutdown() {
		frame.dispose();
	}

	public ScreenUtil() {
		frame = new Frame();
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
	}
}
