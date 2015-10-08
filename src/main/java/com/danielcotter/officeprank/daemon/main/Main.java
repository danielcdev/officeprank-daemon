package com.danielcotter.officeprank.daemon.main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;

import com.danielcotter.officeprank.daemon.util.HttpUtil;
import com.danielcotter.officeprank.daemon.util.ScareUtil;

public class Main {

	private HttpUtil httpUtil = new HttpUtil();
	private ScareUtil scareUtil = new ScareUtil();

	private void begin() throws NoSuchAlgorithmException,
			UnsupportedOperationException, ClientProtocolException,
			IOException, InterruptedException {
		Thread t = null;

		while (true) {
			Boolean isArmed = httpUtil.isArmed();

			if (!isArmed && t != null) {
				t.interrupt();
				t = null;
			} else if (isArmed && t == null) {
				t = scareUtil.arm();
			}

			Thread.sleep(10000);
		}
	}

	public Main() {
		try {
			begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new Main();
	}
}
