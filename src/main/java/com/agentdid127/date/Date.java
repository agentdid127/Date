package com.agentdid127.date;

public class Date {

     private int year, month, day, hour, minute, second, milli;
     private int offset;


     public Date() {
         year = 1970;
         month = 1;
         day = 1;
         hour = 0;
         minute = 0;
         second = 0;
         milli = 0;
     }
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
     
     public Date(int year, int month, int day, int hour, int minute, int second, int milli) {
         this.year = year;
         this.month = month;
         this.day = day;
         this.hour = hour;
         this.minute = minute;
         this.second = second;
         this.milli = milli;
     }

     public boolean isLeapYear() {
         return isLeapYear(year);
     }

     private boolean isLeapYear(int year) {
         return (year % 4 == 0);
     }

     public int getDaysInMonth() {
         return getDaysinMonth(month, year);
     }

     private int getDaysinMonth(int month, int year) {
         if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
             return 31;
         else if (month == 2) {
             if (isLeapYear(year)) return 29;
             else return 28;
         }
         else if (month == 4 || month == 6 || month == 9 || month == 11)
             return 30;
         else if (month < 1) return getDaysinMonth(month + 12, year);
         else if (month > 12) return getDaysinMonth(month - 12, year);
         else return -1;
     }

     public long getMilliTimestamp() {
         return this.diff(new Date(1970, 1, 1, 0, 0,0, 0)).getMilliseconds();
     }
     public long getMilliseconds() {
         long millin = this.milli;
         long secondn = this.second * 1000;
         long minuten = this.second * (1000 * 60);
         long hourn = this.second * (1000 * 60 * 60);
         long dayn = this.day * (1000 * 60 * 60 * 24);
         long monthn = getDaysInMonth() * (1000 * 60 * 60 * 24);

         long leapYears = ((year / 4) * 366) * (1000 * 60 * 60 * 24);
         long normalYears = (year - leapYears) * 365 * (1000 * 60 * 60 * 24);

         long yearn = leapYears + normalYears;

         return millin + secondn + minuten + hourn + dayn + monthn + yearn;

     }

    public Date sum(Date date) {
        Date newDate = new Date(year + date.year, month + date.month, day + date.day,hour + date.hour, minute + date.minute, second + date.second, milli + date.milli);
        return newDate;
    }

     public Date diff(Date date) {
      int leapYears = (year - date.year)/ 4;
      Date newDate = new Date(year - date.year, month - date.month, day - date.day,hour - date.hour, minute - date.minute, second - date.second, milli - date.milli);
      return newDate;
     }




     public String toString() {
         return (year + ":" + getValue(month , 2) + ":" + getValue(day, 2) + ":" + getValue(hour, 2) + ":" + getValue(minute, 2) + ":" + getValue(second, 2)+ ":"+ getValue(milli, 4));
     }

     private String getValue(int in, int length) {
         String inS = String.valueOf(in);
         if (inS.length() < length) {
             int l = length - inS.length();
             for (int i = 0; i < l; i++) {
                 inS = "0" + inS;
             }
         }
         return inS;
     }

     public static String getExample() {
         return "2021:09:06:12:23:12:236";
     }
}
