package fi.tuni.prog3.junitattainment;
import java.util.*;
public class Attainment implements Comparable<Attainment>{
   
    private final String courseCode;
    private final String studentNumber;
    private final int grade;
    
    public Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %d", courseCode, studentNumber,
                grade);
    }

    @Override
    public int compareTo(Attainment other) {
        // ensin op numeroiden vertailu
        int cmp = studentNumber.compareTo(other.studentNumber);
        if ( cmp == 0 ){
            // jos vertalusta samat -> kurssikoodien vertailu
            cmp = courseCode.compareTo(other.courseCode);
        }
        return cmp;
    }
    
    // luokkavakiot
    public final static Comparator<Attainment> CODE_STUDENT_CMP = 
                                                            new Comparator<>(){
        @Override
        public int compare(Attainment a, Attainment b) {
            // ensin kurssikoodin vertailu
            int cmp = a.courseCode.compareTo(b.courseCode);
            if ( cmp == 0 ){
                // toisena op numeron vertailu 
                cmp = a.studentNumber.compareTo(b.studentNumber);
            }
            return cmp;
        }
    };
    
    public final static Comparator<Attainment> CODE_GRADE_CMP = 
                                                        new Comparator<>(){
       @Override
        public int compare(Attainment a, Attainment b) {
            // ensin kurssikoodin vertailu
            int cmp = a.courseCode.compareTo(b.courseCode);
            if ( cmp == 0 ){
                // arvosanan vertailu
                cmp = Integer.compare(b.grade, a.grade);
            }
            return cmp;
        } 
    };
    
}
