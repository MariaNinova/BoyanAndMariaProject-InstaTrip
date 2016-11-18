package users;

import exceptions.AccountException;

public class Admin extends Account{

	public Admin(String username, String password, String email, String firstName, String lastName)
			throws AccountException {
		super(username, password, email, firstName, lastName);

	}

}
