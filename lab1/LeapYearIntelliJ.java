public class LeapYearIntelliJ {
    private static void checkLeapYear(int year){
        if(isLeapYear(year)){
            System.out.println(year + " is a leap year.");
        }else {
            System.out.println(year + " is not a leap year.");
        }
    }

    private static boolean isLeapYear(int year){
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args){
        checkLeapYear(2004);
    }
}
