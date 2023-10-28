public class Date {
    private int year;
    private int month;
    private int day;

    // koodi ekalta viikolta
    static boolean isLeapYear(int year) {
    // Karkausvuosi: jaollinen 4:llä ja ei jaollinen 100:lla tai jaollinen 400:lla.
    // Javan loogisaritmeettiset operaatiot ja return-lause kuin C-kielessä.
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    // Kuukausien päivien määrittämisen voi tehdä monella tavalla. Tässä käytetään
    // taulukkosyntaksin lisähavainnollistamiseksi kaksiulotteista esitäytettyä
    // int-taulukkoa: kullekin kuukaudelle 2-alkioinen alitaulukko, jossa päivien määrä
    // tavallisena ja karkausvuonna (ainoa ero helmikuussa eli toisessa alitaulukossa).
    private static final int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                          {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};

    // Funktio monthDays palauttaa tiedon, kuinka monta päivää kuukaudessa
    // month vuonna year on. Palautusarvo -1 vastaa virheellistä kuukautta.
    static int monthDays(int month, int year) {
    int days = -1;
    if(1 <= month && month <= 12) {
      // Ehdollinen operaattori kuin C-kielessä.
      days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
    }
    return days;
    }

    // Funktio isLegalDate tutkii, onko parametrien day, month ja year kuvaama
    // päivämäärä laillinen. Tässä vuosiluvun oletetaan olevan aina laillinen.
    static boolean isLegalDate(int day, int month, int year) {
    // Tuloksen laskenta on suoraviivaista, koska monthDays
    // palauttaa -1, jos kuukausi on laiton.
    return (1 <= day) && (day <= monthDays(month, year));
    }
    
    // itse luokka 
    public Date(int year, int month, int day) throws DateException {
        
        if ( isLegalDate(day, month, year) ){
            this.year = year;
            this.month = month;
            this.day = day;
        }
        else {
            throw new DateException(String.format("Illegal date "
                    +day+"."+month+"."+year));
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
    
    @Override
    public String toString() {
        /*
        String sdate = Integer.toString(day);
        String smonth = Integer.toString(month);
        String syear= Integer.toString(year);
        */
        return String.format("%02d.%02d.%d",day,month,year);
    }
}