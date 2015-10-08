package com.danielcotter.officeprank.daemon.config;

public class Config {
	
	private static final String url = "http://52.89.60.124:8080/officeprank/daemonstatus";
	private static final String secret = "asdfGhJkl:zxcvbnm,>qWerTyuiop123$56&890";

	/**
	 * @return the url
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * @return the secret
	 */
	public static String getSecret() {
		return secret;
	}
}
