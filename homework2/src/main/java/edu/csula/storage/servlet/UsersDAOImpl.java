package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		//       username/password into the session
		Object data = context.getAttribute(CONTEXT_NAME);
		List<User> tempList;
		if (data == null) {
			tempList = new ArrayList<>();
		} else {
			tempList = (List<User>) data;
		}
		if (username == "admin" && password == "cs3220password") {
			User tempUser = new User(0, username, password);
			context.setAttribute(CONTEXT_NAME, tempUser);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any
		Optional<User> userOp = Optional.empty();
		User tempUser = (User) context.getAttribute(CONTEXT_NAME);
		userOp = Optional.of(tempUser);

		return userOp;
	}

	@Override
	public void logout() {
		// TOOD: log user out using `invalidate`
		HttpSession tempSession = (HttpSession) context.getSessionContext();
		if (tempSession != null) {
			tempSession.invalidate();
		}

	}
}
