package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.*;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	// use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM events;";
	protected static final String getByIdQuery = "SELECT * FROM events " + "WHERE id = ?";
	protected static final String setQuery = "UPDATE events SET name = ?, description = ?, trigger_at = ?"
			+ " WHERE id = ?;";
	protected static final String addQuery = "INSERT INTO events" + 
	" VALUES (?, ?, ?, ?, ?);";
	protected static final String removeQuery = "";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		List<Event> result = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Event entry = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				result.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;
	}

	@Override
	public Optional<Event> getById(int id) {
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			Optional<Event> actualEvent = Optional.empty();
			PreparedStatement ps = c.prepareStatement(getByIdQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Event entry = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			actualEvent = Optional.of(entry);
			return actualEvent;

		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void set(int id, Event event) {
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			PreparedStatement ps = c.prepareStatement(setQuery);
			ps.setString(1, event.getName());
			ps.setString(2, event.getDescription());
			ps.setInt(3, event.getTriggerAt());
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Event event) {
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			PreparedStatement ps = c.prepareStatement(addQuery);
			ps.setInt(1, event.getId());
			ps.setString(2, event.getName());
			ps.setString(3, event.getDescription());
			ps.setInt(4, event.getTriggerAt());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id
	}
}
