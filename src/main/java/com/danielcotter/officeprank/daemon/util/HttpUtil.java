package com.danielcotter.officeprank.daemon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.danielcotter.officeprank.daemon.config.Config;
import com.danielcotter.officeprank.daemon.json.ArmedRequest;
import com.danielcotter.officeprank.daemon.json.ArmedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	private static final HttpClient client = HttpClientBuilder.create().build();
	private static final ObjectMapper mapper = new ObjectMapper();

	public Boolean isArmed() throws NoSuchAlgorithmException,
			UnsupportedOperationException, ClientProtocolException, IOException {
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		ArmedRequest request = new ArmedRequest(String.valueOf(System
				.currentTimeMillis()), EncryptionUtil.shaHash(String
				.valueOf(System.currentTimeMillis()) + Config.getSecret()));

		postData.add(new BasicNameValuePair("json", mapper
				.writeValueAsString(request)));

		ArmedResponse response = (ArmedResponse) doRequest(ArmedResponse.class,
				Config.getUrl(), postData);

		return response.getArmed();
	}

	private Object doRequest(Object mappingObject, String url,
			List<BasicNameValuePair> postData)
			throws UnsupportedOperationException, ClientProtocolException,
			IOException {
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(postData));
		String data = getReply(client.execute(post));

		return mapper.readValue(data, (Class<?>) mappingObject);
	}

	private String getReply(HttpResponse response)
			throws UnsupportedOperationException, IOException {
		BufferedReader myReader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String line;
		String data = "";

		while ((line = myReader.readLine()) != null)
			data += line;

		return data;
	}
}
