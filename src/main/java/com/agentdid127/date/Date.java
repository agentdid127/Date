package com.agentdid127.date;

public class Date {

     //Instance Variables
     private final int year, month, day, hour, minute, second, milli;


    /**
     * Basic object stated at the UNIX start time
     */
    public Date() {
         year = 1970;
         month = 1;
         day = 1;
         hour = 0;
         minute = 0;
         second = 0;
         milli = 0;
     }

    /**
     * Date started as a string Date object
     * @param dateIn Date object
     */
    public Date(String dateIn) {
         String[] date = dateIn.split(":");
             year = Integer.parseInt(date[0]);
             month = Integer.parseInt(date[1]);
             day = Integer.parseInt(date[2]);
             hour = Integer.parseInt(date[3]);
             minute = Integer.parseInt(date[4]);
             second = Integer.parseInt(date[5]);
             milli = Integer.parseInt(date[6]);
     }

    /**
     * Date, but with all arguments as an integer
     * @param year year
     * @param month month
     * @param day day
     * @param hour hour
     * @param minute minute
     * @param second second
     * @param milli millisecond
     */
     public Date(int year, int month, int day, int hour, int minute, int second, int milli) {
         this.year = year;
         this.month = month;
         this.day = day;
         this.hour = hour;
         this.minute = minute;
         this.second = second;
         this.milli = milli;
     }

    /**
     * Checks if the year in the Date object is a leap year
     * @return boolean variable of a leap year
     */
     public boolean isLeapYear() {
         return isLeapYear(year);
     }

    /**
     * Checks if a year is a leap year
     * @param year year to check
     * @return boolean variable of if it's a leap year
     */
     private boolean isLeapYear(int year) {
         return (year % 4 == 0);
     }


    /**
     * Gets the days in the Date object's month.
     * @return number of days in current month
     */
    public int getDaysInMonth() {
         return getDaysInMonth(month, year);
     }

    /**
     * Calculates the number of days in the current month
     * @param month month to check
     * @param year year of month to check (for the case of Feb.)
     * @return integer, number of days in the month
     */
     private int getDaysInMonth(int month, int year) {
         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
             return 31;
         else if (month == 2) {
             if (isLeapYear(year)) return 29;
             else return 28;
         }
         else if (month == 4 || month == 6 || month == 9 || month == 11)
             return 30;
         else if (month < 1) return getDaysInMonth(month + 12, year);
         else return getDaysInMonth(month - 12, year);
     }

    /**
     * Gets the current timestamp using the UNIX date format.
     * @return timestamp in UNIX format.
     */
    public long getMilliTimestamp() {
        long secondN = this.second  * 1000L;
         long minuteN = this.minute * (1000 * 60);
         long hourN = this.hour * (1000 * 60 * 60);
         long dayN = (this.day) * (1000 * 60 * 60 * 24);
         long monthN = 0;
         for (int i = 1; i < month; i++) monthN += getDaysInMonth(i, year);

         monthN *= (1000 * 60 * 60 * 24);
         int yearTemp = year - 1970;
         long leapYears = yearTemp / 4;
         long normalYears = (yearTemp - leapYears) * (1000 * 60 * 60 * 24) * 365L;
         leapYears *= (1000 * 60 * 60 * 24) * 366L;
         long yearN = leapYears + normalYears;

         return (long) this.milli + secondN + minuteN + hourN + dayN + monthN + yearN;

     }

    /**
     * Adds the two dates
     * @param date Date to add
     * @return Date object of added date
     */
    public Date sum(Date date) {
        return new Date(year + date.year, month + date.month, day + date.day,hour + date.hour, minute + date.minute, second + date.second, milli + date.milli);
    }

    /**
     * Subtracts the dates
     * @param date Date to subtract
     * @return Date object of subtracted date
     */
     public Date diff(Date date) {
         return new Date(year - date.year, month - date.month, day - date.day,hour - date.hour, minute - date.minute, second - date.second, milli - date.milli);
     }

    /**
     * Gets date object with a Timezone offset
     * @param offset Timezone offset Ex: if it's UTC-4, write -4
     * @return Date object in local form.
     */
     public Date localDate(int offset) {
         int day = this.day, month = this.month, year = this.year;
         int hour = this.hour +  offset;
         if (hour < 0 || hour > 23) {
             while (hour < 0) {
                 day--;
                 hour += 24;
             }
             while (hour > 23) {
                 hour -= 24;
                 day++;
             }
         }
         if (day > getDaysInMonth(month, year) || day < 1) {
             while (day > getDaysInMonth(month, year)) {
                 day -= getDaysInMonth(month, year);
                 month += 1;

             }
             while (day < 1) {
                 day += getDaysInMonth(month, year);
                 month -= 1;
             }
         }
         if (month > 12 || month < 0) {
             while (month > 12) {
                 month -= 12;
                 year++;
             }
             while (month < 1) {
                 month += 12;
                 year--;
             }
         }
         if (year < 0) year--;


         return new Date(year, month, day, hour, minute, second, milli);
     }


    /**
     * Converts Date object back to string form.
     * @return String form of Date
     */
     public String toString() {
         return (year + ":" + getValue(month , 2) + ":" + getValue(day, 2) + ":" + getValue(hour, 2) + ":" + getValue(minute, 2) + ":" + getValue(second, 2)+ ":"+ getValue(milli, 3));
     }

    /**
     * Makes sure that the String format uses zeros where needed.
     * @param in Article to update
     * @param length how many zeroes to add if needed.
     * @return String of int, with zeroes added if needed
     */
     private String getValue(int in, int length) {
         StringBuilder inS = new StringBuilder(String.valueOf(in));
         if (inS.length() < length) {
             int l = length - inS.length();
             for (int i = 0; i < l; i++) {
                 inS.insert(0, "0");
             }
         }
         return inS.toString();
     }

    /**
     * Returns an example String date, to show off how it could work.
     * @return String example date.
     */
     public static String getExample() {
         return "2021:09:06:12:23:12:236";
     }
}
