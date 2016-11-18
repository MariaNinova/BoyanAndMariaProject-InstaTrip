package destination;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import exceptions.ReservationException;
import exceptions.RoomException;

public class Room {

	private int number;
	protected String type;
	private int priceForNight;
	private byte numAdults;
	private byte numChildren;
	private static Set<String> allRoomFacilites = new HashSet<String>();
	private Set<String> roomFacilities;
	private Set<Reservation> reservations;

	public Room(int priceForNight, byte numAdults, byte numChildren, int numOfRoom) throws RoomException {
		this.number = numOfRoom;
		setType(numAdults, numChildren);
		if (priceForNight > 0) {
			this.priceForNight = priceForNight;
		} else {
			throw new RoomException("Invalid price for the room!");
		}
		if (numChildren >= 0) {
			this.numChildren = numChildren;
		}

		else {
			throw new RoomException("Invalid number of children");
		}
		if (numAdults > 0) {
			this.numAdults = numAdults;
		}

		else {
			throw new RoomException("Invalid number of adults");
		}
		roomFacilities = new HashSet<String>();
		reservations = new TreeSet<Reservation>(new ReservationComparator());
	}

	@Override
	public String toString() {
		return type + " [number=" + number + ", price =" + priceForNight + ", numAdults=" + numAdults + ", numChildren="
				+ numChildren + "]";

	}

	public static String checkType(int numAdults, int numChildren) throws RoomException {
		if (numAdults > 0) {
			if (numChildren == 0) {
				switch (numAdults) {
				case 1:
					return "Single Room";
				case 2:
					return "Double Room";
				case 3:
					return "Triple Room";
				case 4:
					return "Apart for four";
				case 5:
				case 6:
					return "Apart for six";
				default:
					return "Apart for six+";

				}
			}
			if (numChildren > 0) {

				int numOfAll = numAdults + numChildren;
				if (numAdults > 0 && numChildren > 0) {
					if (numAdults <= 2) {
						switch (numChildren) {
						case 1:
							return "Double Room+1";
						case 2:
							return "Double Room+2";
						}
					}
					if (numAdults == 3 && numChildren == 1)
						return "Apart for four";

					if (numOfAll > 4 && numOfAll < 6)
						return "Apart for six";

					if (numOfAll > 6)
						return "Apart for six+";
				}
			}
		}

		return "Double Room";

	}

	private void setType(byte numAdults, byte numChildren) {
		if (numAdults > 0 && numChildren == 0) {
			switch (numAdults) {
			case 1:
				type = "Single Room";
				break;
			case 2:
				type = "Double Room";
				break;
			case 3:
				type = "Triple Room";
				break;
			case 4:
				type = "Apart for four";
				break;
			case 5:
			case 6:
				type = "Apart for six";
				break;
			default:
				type = "Apart for six+";
				break;
			}
		} else {
			int numOfAll = numAdults + numChildren;
			if (numAdults > 0 && numChildren > 0) {
				if (numAdults <= 2) {
					switch (numChildren) {
					case 1:
						type = "Double Room+1";
						break;
					case 2:
						type = "Double Room+2";
						break;
					}
				}
				if (numAdults == 3 && numChildren == 1)
					type = "Apart for four";

				if (numOfAll > 4 && numOfAll < 6)
					type = "Apart for six";

				if (numOfAll > 6)
					type = "Apart for six+";

			}
		}
	}

	public boolean addReservation(LocalDate startDay, LocalDate endDay) throws ReservationException {
		Reservation newRes = new Reservation(startDay, endDay);

		if (!reservations.contains(newRes)) {
			this.reservations.add(newRes);
			System.out.println("The room number " + this.number + " is reserved at " + LocalDateTime.now());
			return true;
		}
		return false;
	}

	public void printReservations() {
		for (Reservation res : this.reservations) {
			System.out.println(res);
		}
	}

	protected class Reservation {

		private LocalDate startDate;
		private LocalDate endDate;

		Reservation(LocalDate startDay, LocalDate endDay) throws ReservationException {
			if (startDay != null && endDay != null) {
				if (startDay.compareTo(endDay) > 0)
					throw new ReservationException("Invalid reservation! Check your dates again!");
				else {
					this.startDate = startDay;
					this.endDate = endDay;
				}
			} else {
				throw new ReservationException("Invalid reservation! Dates cannot be null!");
			}
		}

		Reservation(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay)
				throws ReservationException {

			if (isDateValid(startYear, startMonth, startDay) && (isDateValid(endYear, endMonth, endDay))) {
				if (LocalDate.of(startYear, startMonth, startDay)
						.compareTo(LocalDate.of(endYear, endMonth, endDay)) < 0) {
					this.startDate = LocalDate.of(startYear, startMonth, startDay);
					this.endDate = LocalDate.of(endYear, endMonth, endDay);
				} else {
					throw new ReservationException("Start date should be before end date");
				}
			}
		}

		private boolean isDateValid(int year, int month, int day) throws ReservationException {
			if (year < LocalDate.now().getYear() || month < 0 || month > 12 || day < 1)
				throw new ReservationException("The date is invalid! Please, try again!");
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (day > 31) {
					throw new ReservationException("The day cannot be bigger than 31 for this month!");
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (day > 30) {
					throw new ReservationException("The day cannot be bigger than 30 for this month!");
				}
				break;
			case 2:
				if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && day > 29) {
					throw new ReservationException(year + " is leap year! February cannot have more than 29 days.");
				} else if (day > 28) {
					throw new ReservationException("Days during this year cannot be more than 28.");
				}
				break;
			}
			return true;
		}

		@Override
		public String toString() {
			return "Reservation [startDay=" + startDate + ", endDay=" + endDate + "]";
		}

	}

	public class ReservationComparator implements Comparator<Reservation> {

		@Override
		public int compare(Reservation tempRes, Reservation newRes) {
			if ((newRes.startDate.compareTo(tempRes.endDate) < 0 && tempRes.endDate.compareTo(newRes.endDate) < 0)
					|| (newRes.startDate.compareTo(tempRes.startDate) < 0
							&& tempRes.startDate.compareTo(newRes.endDate) < 0)
					|| (tempRes.startDate.compareTo(newRes.startDate) < 0
							&& newRes.endDate.compareTo(tempRes.endDate) < 0)) {
				return 0;
			} else {
				return tempRes.startDate.compareTo(newRes.startDate);
			}

		}
	}

	public String getType() {
		return type;
	}

	public boolean isFree(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay)
			throws ReservationException {

		if (!reservations.contains(new Reservation(startYear, startMonth, startDay, endYear, endMonth, endDay))) {
			return true;
		}
		return false;
	}

}
