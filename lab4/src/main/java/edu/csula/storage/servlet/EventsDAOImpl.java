package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

import javax.servlet.ServletContext;

import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";
	private List<Event> eventList;

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
		this.eventList = new ArrayList<Event>();
	}

	@Override
	public List<Event> getAll() {
		// TODO: read a list of events from context
		Object data = context.getAttribute(CONTEXT_NAME);
		if (data == null) {
			return new ArrayList<>();
		} else {
			return (List<Event>) data;
		}
	}

	@Override
	public Optional<Event> getById(int id) {
		Optional<Event> actualEvent = Optional.empty();
		List<Event> dataList = (List<Event>) context.getAttribute(CONTEXT_NAME);

		for(int i = 0; i < dataList.size(); i++){
			if ( dataList.get(i).getId() == id) {
				 actualEvent = Optional.of(dataList.get(i));
			}
		}

		return actualEvent;
	}

	@Override
	public void set(int id, Event event) {
		// TODO: set a certain event given id to be different from context
	}

	@Override
	public void add(Event event) {
		this.eventList.add(event);
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single event given id
	}
}
