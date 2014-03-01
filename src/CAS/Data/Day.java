package CAS.Data;

/**
 * Day is an enum where we set a letter to each day of the week.
 * The days are constants and are set to one letter in the toString. 
 * 
 * @author Sarah Ben-Kiki
 *
 */
public enum Day {SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	THURSDAY, FRIDAY, SATURDAY, ANY;

/**
 * This is the toSting that assigns the constant 
 * with its letter value. If it is assigned something
 * false it will through out an exception.
 */
@Override
public String toString() {
	switch (this) {
	case SUNDAY: return "Su";
	case MONDAY: return "M";
	case TUESDAY: return "T";
	case WEDNESDAY: return "W";
	case THURSDAY: return "R";
	case FRIDAY: return "F";
	case SATURDAY: return "Sa";
	case ANY: return " ";
	default: throw new IllegalArgumentException();
	}
}
}