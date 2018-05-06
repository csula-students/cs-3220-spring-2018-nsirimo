package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.List;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

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
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		Object data = context.getAttribute(CONTEXT_NAME);
		if (data == null) {
			return new ArrayList<>();
		} else {
			return (List<Generator>) data;
		}
	}

	@Override
	public Optional<Generator> getById(int id) {
		Optional<Generator> actualGen = Optional.empty();
		List<Generator> dataList = (List<Generator>) context.getAttribute(CONTEXT_NAME);

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getId() == id) {
				actualGen = Optional.of(dataList.get(i));
			}
		}

		return actualGen;
	}

	@Override
	public void set(int id, Generator generator) {
		List<Generator> tempList = getAll();
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getId() == id) {
				tempList.get(i).setName(generator.getName());
				tempList.get(i).setDescription(generator.getDescription());
				tempList.get(i).setRate(generator.getRate());
				tempList.get(i).setBaseCost(generator.getBaseCost());
				tempList.get(i).setUnlockAt(generator.getUnlockAt());
			}
		}

		context.setAttribute(CONTEXT_NAME, tempList);
	}

	@Override
	public void add(Generator generator) {
		List<Generator> tempList = getAll();
		tempList.add(generator);

		context.setAttribute(CONTEXT_NAME, tempList);
	}

	@Override
	public void remove(int id) {
		List<Generator> tempList = getAll();
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getId() == id) {
				tempList.remove(i);
			}
		}

		context.setAttribute(CONTEXT_NAME, tempList);
	}
}