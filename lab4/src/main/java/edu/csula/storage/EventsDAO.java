package edu.csula.storage;

import java.util.Collection;
import java.util.Optional;

import edu.csula.models.Event;

/**
 * EventsDAO defines the database access layer between the business layer and
 * the database layer
 */
public interface EventsDAO {
	/**
	 * Find all events given storage
	 */
	public Collection<Event> getAll();
	/**
	 * Find certain event given its id
	 */
	public Optional<Event> getById(int id);
	/**
	 * Change a event given its id
	 */
	public void set(int id, Event event);
	/**
	 * addEvent adds a new event to database storage layer
	 */
	public void add(Event event);
}
