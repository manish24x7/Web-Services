package com.myproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.bean.Track;
import com.myproject.service.TrackServicce;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;


/**
 * Servlet implementation class TrackController
 */
@WebServlet("/TrackController")
public class TrackController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrackController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getParameter("action");
		TrackServicce trackServicce = new TrackServicce();
		if("View".equalsIgnoreCase(action)){
			request.setAttribute("trackList", trackServicce.getAllTracks());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewTracks.jsp");
			requestDispatcher.forward(request, response);
		}
		else if("Save".equalsIgnoreCase(action)){
			Track track = new Track();
			track.setTitle(request.getParameter("title"));
			track.setSinger(request.getParameter("singer"));
			boolean result = trackServicce.saveTrack(track, request.getParameter("dbType"));
			//navigating to success page
			if(result){
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Success.jsp");
				requestDispatcher.forward(request, response);
			}
		}else if("Search".equalsIgnoreCase(action)){
			String searchCriteria = request.getParameter("criteria");
			String searchText = request.getParameter("searchText");
			List<Track> trackList = trackServicce.searchForTracks(searchCriteria,searchText);
			request.setAttribute("trackList", trackList);
			request.setAttribute("mode", "results");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("FindTrack.jsp");
			requestDispatcher.forward(request, response);
		}

	}
}
