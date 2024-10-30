package vttp.batch5.sdf.task01;

public class MoreUtilities extends Utilities{
   	public static String holidayString(boolean holiday){
		if (holiday == true){
			return "a holiday";
		}
		else return "not a holiday";
	}

	public static String toDay(int weekday) {
		switch (weekday) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return DAY[weekday];
			default:
				return "incorrect day";
		}
	}
}
