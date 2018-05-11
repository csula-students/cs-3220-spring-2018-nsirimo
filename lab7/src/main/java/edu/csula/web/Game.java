package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.models.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(){
		EventsDAO eventsDao = new EventsDAOImpl(getServletContext());
		if(eventsDao.getAll().size() == 0){
			eventsDao.add(new Event(0, "Doge Coins Flowing", "", 10));
			eventsDao.add(new Event(1, "You Feel Strangely Powerful, and Bork!", "Bork Bork Bork Bork Bork Bork *laughs in Dog*", 20));
			eventsDao.add(new Event(2, "You hear a Woof Woof in the distance...", "Woof so strong the coins just flow out!", 60));
        }

		GeneratorsDAO generatorDao = new GeneratorsDAOImpl(getServletContext());
		if(generatorDao.getAll().size() == 0){
			GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
			generatorDao.add(new Generator(0, "Doge Click", "The power of doge is in every click!", 5, 10, 10));
			generatorDao.add(new Generator(1, "Bork Power", "*laughs in Doge* BORK BORK BORK!!!", 10, 100, 100));
			generatorDao.add(new Generator(2, "Woofer", "The subwoofers are breaking! Off the chain bruh!", 20, 500, 500));
		}
	}
}