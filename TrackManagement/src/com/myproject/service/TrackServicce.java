package com.myproject.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.bean.Track;

public class TrackServicce {

	public boolean saveTrack(Track track,String dbType){
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/RestWebservice/rest/json/metallica/post/"+dbType);

			ObjectMapper mapper = new ObjectMapper();

			String input = mapper.writeValueAsString(track);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Track> getAllTracks(){

		List<Track> trackList = new ArrayList<Track>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			URL url = new URL("http://localhost:8080/RestWebservice/rest/json/metallica/get");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				trackList = Arrays.asList(mapper.readValue(output, Track[].class));
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trackList;
	}

	public List<Track> searchForTracks(String searchCriteria, String searchText){
		List<Track> trackList = new ArrayList<Track>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			URL url = new URL("http://localhost:8080/RestWebservice/rest/json/metallica/get/"+searchCriteria+"/"+searchText);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				trackList = Arrays.asList(mapper.readValue(output, Track[].class));
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trackList;
	}

}
