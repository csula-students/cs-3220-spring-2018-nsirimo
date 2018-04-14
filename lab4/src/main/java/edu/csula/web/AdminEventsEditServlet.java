package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events/edit")
public class AdminEventsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = null;
        EventsDAO dao = new EventsDAOImpl(getServletContext());
        int eventId = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Collection<Event> events = dao.getAll();

        for (Event tempEvent : events) {
            if (tempEvent.getId() == eventId) {
                event = tempEvent;
            }
        }

        out.println("<html>");
        out.println("	<head>");
        out.println("		<meta charset=\"UTF-8\">");
        out.println("		<title>Doge Coin Game</title>");
        out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"../custom.css\">");
        out.println("	</head>");
        out.println("	<body>");
        out.println("		<div class=\"main\">");
        out.println("			<h1 class=\"pageTitle\">Event Editor</h1>");
        out.println("			<nav class=\"navBar\">");
        out.println("				<a href=\"admin-info.html\">Game Information</a>");
        out.println("				<a href=\"admin-generators.html\">Coin Generators</a>");
        out.println("				<a href=\"admin-events.html\">");
        out.println("    				<div class=\"navTitle\">Events</div>");
        out.println("				</a>");
        out.println("			</nav>");
        out.println("			</div>");
        out.println("			<div class = \"container\">");
        out.println("				<div class = \"genForm\">");
        out.println("					<form method = \"POST\">");
        out.println("						<label for = \"event_name\">Event Name:</label>");
        out.println("							<input type=\"text\" id=\"event_name\" name=\"event_name\" value=\""
                + event.getName() + "\"></input>");
        out.println("						<label for = \"description\">Event Description</label>");
        out.println("							<input type=\"text\" id=\"description\" name=\"description\" value=\""
                + event.getDescription() + "\"></input>");
        out.println("						<label for = \"trigger\">Trigger at</label>");
        out.println("							<input type=\"text\" id=\"trigger\" name=\"trigger\" value=\""
                + event.getTriggerAt() + "\" required></input>");
        out.println("                           <input type=\"hidden\" name=\"id\" value=\"" + eventId + "\"></input>");
        out.println("							<input type=\"submit\" value=\"hitme!\"></input>");
        out.println("					</form>");
        out.println("				</div>");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post hit");
        String name = request.getParameter("event_name");
        String description = request.getParameter("description");
        String triggerAt = request.getParameter("trigger");
        int id = Integer.parseInt(request.getParameter("id"));

        Collection<Event> events = new EventsDAOImpl(getServletContext()).getAll();

        Event e = new Event(id, name, description, Integer.parseInt(triggerAt));
        new EventsDAOImpl(getServletContext()).set(id, e);

        response.sendRedirect("../../admin/events");
    }
}