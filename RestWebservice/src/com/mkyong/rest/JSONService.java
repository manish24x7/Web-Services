package com.mkyong.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.Track;

@Path("/json/metallica")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTrackInJSON() {

		List<Track> trackList = new ArrayList<Track>();
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/trackingdb";
			Class.forName(myDriver);
			//System.out.print(title + " " + singer);
			Connection conn = DriverManager.getConnection(myUrl, "root", "");
		
			String query = "select title, singer from table1";
			getTrackBasedOnCriteria(conn,trackList,query);
			
			query = "select title, singer from table2";
			getTrackBasedOnCriteria(conn,trackList,query);
			
			query = "select title, singer from table3";
			getTrackBasedOnCriteria(conn,trackList,query);
			
		    conn.close();
		}
		catch (Exception e)
	    {
			e.printStackTrace();
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
		return trackList;

	}
	
	@GET
	@Path("/get/{searchCriteria}/{searchText}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> searchForTrack(@PathParam("searchCriteria") String searchCriteria, 
			@PathParam("searchText") String searchText) {

		List<Track> trackList = new ArrayList<Track>();
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/trackingdb";
			Class.forName(myDriver);
			//System.out.print(title + " " + singer);
			Connection conn = DriverManager.getConnection(myUrl, "root", "");
		
			String query = "select title, singer from table1 where "+searchCriteria+" like '"+searchText+"%'";
			getTrackBasedOnCriteria(conn,trackList,query);
			
			query = "select title, singer from table2 where "+searchCriteria+" like '"+searchText+"%'";
			getTrackBasedOnCriteria(conn,trackList,query);
			
			query = "select title, singer from table3 where "+searchCriteria+" like '"+searchText+"%'";
			getTrackBasedOnCriteria(conn,trackList,query);
			
		    conn.close();
		}
		catch (Exception e)
	    {
			e.printStackTrace();
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
		return trackList;

	}
	
	private List<Track> getTrackBasedOnCriteria(Connection conn,List<Track> trackList,String query) throws SQLException{
		PreparedStatement preparedStmt = conn.prepareStatement(query);				
		ResultSet rs = preparedStmt.executeQuery();
		Track track = null;
		while(rs.next()){
			track = new Track();
			track.setTitle(rs.getString(1));
			track.setSinger(rs.getString(2));
			trackList.add(track);
		}
		return trackList;
	}

	@POST
	@Path("/post/{dbType}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track,@PathParam("dbType") String dbType) {

		String result = "Track saved : " + track;
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/trackingdb";
			Class.forName(myDriver);
			//System.out.print(title + " " + singer);
			Connection conn = DriverManager.getConnection(myUrl, "root", "");
		
			String query = " insert into "+dbType+" (Title, Singer) values ('"+track.getTitle()+"', '"+track.getSinger()+"')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.executeUpdate();
			
		    conn.close();
		}
		catch (Exception e)
	    {
			e.printStackTrace();
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
		return Response.status(201).entity(result).build();
		
	}
	
}