package CAS.Data;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) throws DateException {
		//check the date for none leap years
		if(month>12 || month<1){
			throw new DateException("incorrect month");
		}
		else
			this.month=month;
		// check leap year febuary for up to 29 days
		if(month==2 && year%4==0){
			if(day>29 || day<1){
				throw new DateException("Incorrect Day ");
			}
                        else {
                            this.day = day;
                        }
		}
		//check nonleap year febuary days
		else if(month==2 && year%4!=0){
			if(day>28 || day<1){
				throw new DateException("Incorrect Day ");
			}
                        else {
                            this.day = day;
                        }
		}
		//check for day in a 31 day month
		else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			if(day>31 || day<1){
				throw new DateException("Incorrect Day ");
			}
                        else {
                            this.day = day;
                        }
		}
		//check for day in a 30 day month
		else if(month==4 || month==6 || month==9 || month==11){
			if(day>30 || day<1){
				throw new DateException("Incorrect Day ");
			}
                        else {
                            this.day = day;
                        }                        
		}
		else
			this.day=day;
		//make sure the input year is not negative
		if(year<=0){
			throw new DateException("incorrect year");
		}
		else
			this.year=year;
	}
	
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
        
        public int compareTo(Date date) {
            int yearComparison = date.getYear() - year;
            if (yearComparison != 0) {
                return yearComparison;
            }
            else {
                int monthComparison = date.getMonth() - month;
                if (monthComparison != 0) {
                    return monthComparison;
                }
                else {
                    int dayComparison = date.getDay() - day;
                    return dayComparison;
                }
            }
        }
        
        public String toString() {
            return "" + day + "/" + month + "/" + year;
        }
}
