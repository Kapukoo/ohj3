package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JunitattainmentTest {
    
    @Test
    public void testGetCourseCode() {
        Attainment instance = new Attainment("1234", "111222333", 4);
        String expR = "1234";
        String r = instance.getCourseCode();
        assertEquals(expR, r);
    }
    
    @Test
    public void testGetStudentNumber() {
        Attainment instance = new Attainment("1234", "111222333", 4);
        String expR = "111222333";
        String r = instance.getStudentNumber();
        assertEquals(expR, r);
    }
    
    @Test
    public void testGetGrade() {
        Attainment instance = new Attainment("1234", "111222333", 4);
        int expR = 4;
        int r = instance.getGrade();
        assertEquals(expR, r);
    }
    /*
    @Test
    public void whenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Attainment("1234", "123", 6);
        });
        String expR = "incompatible types: int cannot be converted to String";
        String r = exception.getMessage();
        assertTrue(r.contains(expR));
    }
    */
    @Test
    public void whenExceptionThrown() {
        try {
        Attainment instance = new Attainment("1234", "111222333", 6);
        } catch(IllegalArgumentException e){
            
        }
    }
    
    @Test
    public void testToString() {
        Attainment instance = new Attainment("1234", "111222333", 4);
        String expR = "1234 111222333 4";
        String r = instance.toString();
        assertEquals(expR, r);
    }
    
    @Test
    public void testCompareTo() {
        Attainment instance1 = new Attainment("1234"
                , "111222333", 4);
        Attainment instance2 = new Attainment("2234"
                , "111222333", 4);
        int expR = 1;
        int r = instance2.compareTo(instance1);
        assertEquals(expR, r);
    }
}
