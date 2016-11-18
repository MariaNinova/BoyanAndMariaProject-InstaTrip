package users;

import java.time.LocalDate;

import destination.Estate;
import destination.Room;
import exceptions.AccountException;
import exceptions.ReservationException;
import exceptions.RoomException;

public class Account extends User {

	private static final int MIN_PASSWORD_SIZE = 6;
	private static final int MIN_USERNAME_SIZE = 4;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	public Account(String username, String password, String email, String firstName, String lastName)
			throws AccountException {
		if (username != null) {
			this.username = username;
		} else {
			throw new AccountException("Invalid username!");
		}
		if (isValidPassword(password)) {
			this.password = password;
		} else {
			throw new AccountException("Invalid password");
		}
		if (isValidPassword(password)) {
			this.email = email;
		} else {
			throw new AccountException("Invalid email!");
		}
		if (isValidName(firstName)) {
			this.firstName = firstName;
		} else {
			throw new AccountException("Invalid first name!");
		}
		if (isValidName(lastName)) {
			this.lastName = lastName;
		} else {
			throw new AccountException("Invalid last name!");
		}
	}

	public void reserve(Estate estate, LocalDate startDay, LocalDate endDay, int numAdults, int numChildren) {
		if (estate != null) {
			try {
				String roomType = Room.checkType(numAdults, numChildren);
				estate.reserveRoom(roomType, startDay, endDay);
			} catch (RoomException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (ReservationException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	private boolean isValidPassword(String password) {
		if (password != null && password.length() >= MIN_PASSWORD_SIZE && !password.matches("[^A-Z]+")
				&& !password.matches("[^a-z]+") && !password.matches("[^0-9]+")) {
			return true;
		}
		return false;
	}

	private boolean isValidEmail(String email) {
		if (email != null)
			return true;
		return false;
	}

	private boolean isValidUsername(String username) {

		if (username != null && username.length() >= MIN_USERNAME_SIZE && username.matches("[A-Za-z0-9]+")) {
			return true;
		}
		return false;
	}

	private boolean isValidName(String name) {
		if (name != null && name.matches("[A-Za-z]+")) {
			return true;
		}
		return false;
	}

	private boolean isValidPhoneNumber(String phone) {
		if (phone != null && phone.matches("[0]+[8]+[7-9]{1}[0-9]{7}"))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
