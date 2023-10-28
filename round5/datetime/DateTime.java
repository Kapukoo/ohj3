public class DateTime extends Date {
    private int hour;
    private int minute;
    private int second;

    public DateTime(int year, int month, int day, int hour, int minute, int second) 
            throws DateException{
        super(year, month, day);
        
        if ( 0<=hour && hour<=23 &&
             0<=minute && minute<=59 &&
             0<=second && second<=59 ){
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
        else {
            throw new DateException(String.format("Illegal time "
                    +hour+":"+minute+":"+second));
        }
        
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
    
    public String toString() {
        
        String supers = super.toString();
        /*
        String shour = Integer.toString(hour);
        String sminute = Integer.toString(minute);
        String ssecond = Integer.toString(second);
        */
        return String.format(supers+" %02d:%02d:%02d",hour,minute,second);
    }
    
}