package com.danielcotter.officeprank.daemon.util;

import java.awt.MouseInfo;

public class MouseUtil {

	public void wakeOnMove() throws InterruptedException {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;

		while (true) {
			if (x != MouseInfo.getPointerInfo().getLocation().x
					|| y != MouseInfo.getPointerInfo().getLocation().y)
				break;

			Thread.sleep(300);
		}
	}
}
