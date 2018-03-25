package edu.csula.models;

public class Event {
	private final int id;
	private final String name;
	private final String description;
	private final int triggerAt;

	public Event(int id, String name, String description, int triggerAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.triggerAt = triggerAt;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getTriggerAt() {
		return triggerAt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final Event event = (Event) obj;
		return this.id == event.id && this.name.equals(event.name) &&
			this.description.equals(event.description) &&
			this.triggerAt == event.triggerAt;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
