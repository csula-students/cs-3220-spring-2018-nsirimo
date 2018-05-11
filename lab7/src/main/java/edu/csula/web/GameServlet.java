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

    public void init() {
        EventsDAO eventsDao = new EventsDAOImpl(getServletContext());
        if (eventsDao.getAll().size() == 0) {
            eventsDao.add(new Event(0, "Doge Coins Flowing", "", 10));
            eventsDao.add(new Event(1, "You Feel Strangely Powerful, and Bork!",
                    "Bork Bork Bork Bork Bork Bork *laughs in Dog*", 20));
            eventsDao.add(new Event(2, "You hear a Woof Woof in the distance...",
                    "Woof so strong the coins just flow out!", 60));
        }

        GeneratorsDAO genDao = new GeneratorsDAOImpl(getServletContext());
        if (genDao.getAll().size() == 0) {
            GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
            genDao.add(new Generator(0, "Doge Click", "The power of doge is in every click!", 5, 10, 10));
            genDao.add(new Generator(1, "Bork Power", "*laughs in Doge* BORK BORK BORK!!!", 10, 20, 20));
            genDao.add(new Generator(2, "Woofer", "The subwoofers are breaking! Off the chain bruh!", 20, 60, 60));
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        GsonBuilder builder = new GsonBuilder();
        Collection<Generator> generators = genDao.getAll();
        Collection<Event> events = eventsDao.getAll();

        Gson gson = builder.create();
        String convertedState = gson.toJson(new State(generators, events));

        request.setAttribute("lastGen", generators.size() - 1);
        request.setAttribute("state", convertedState);
        request.getRequestDispatcher("./WEB-INF/game.jsp").forward(request, response);
    }
}
