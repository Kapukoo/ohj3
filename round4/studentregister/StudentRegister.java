import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class StudentRegister {
    private ArrayList<Student> students = new ArrayList<>(); 
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Attainment> attainments = new ArrayList<>();
    
    public ArrayList<Student> getStudents (){
        students.sort((a,b) -> a.getName().compareTo(b.getName()));
        return students;
    }

    public ArrayList<Course> getCourses() {
        courses.sort((a,b) -> a.getName().compareTo(b.getName()));
        return courses;
    }
    
    public void addStudent(Student student){
        students.add(student);
    }
    
    public void addCourse(Course course){
        courses.add(course);
    }
    
    public void addAttainment(Attainment attainment){
        attainments.add(attainment);
    }
    
    public void printStudentAttainments(String studentNumber, String order){
        
        String name = "";
        for ( Student stud : students ){
            String opn = stud.getStudentNumber();
            if ( opn.equals(studentNumber) ){
                name = stud.getName();
                break;
            }
        }
        
        // no name found
        if ( name.equals("") ){
            System.out.println("Unknown student number: "+studentNumber);
            return;
        }

        // found courses
        ArrayList<Attainment> found = new ArrayList<>();
        System.out.println(name+" ("+studentNumber+"):");
        for ( Attainment att : attainments ) {
            if ( att.getStudentNumber().equals(studentNumber) ){
                found.add(att);
            }
        }
        
        ArrayList<Attainment> orderArray = new ArrayList<>(found);
        if ( order.equals("by name")){
            Collections.sort(orderArray, new Comparator<Attainment>(){  
                public int compare(Attainment a, Attainment b){
                    String aName = "";
                    String bName = "";
                    for ( Course c : courses ){
                        if ( c.getCode().equals(a.getCourseCode())){
                            aName = c.getName();
                        }
                        if ( c.getCode().equals(b.getCourseCode())){
                            bName = c.getName();
                        }
                    }
                    return aName.compareTo(bName);
                }
            });
        }
        if ( order.equals("by code")){
            orderArray.sort((a,b) -> 
                        a.getCourseCode().compareTo(b.getCourseCode()) );
        }
                
        // printing
        for ( Attainment att : orderArray ) {
            String code = att.getCourseCode();
            String cname = "";
            int grade = att.getGrade();
            for ( Course c : courses){
                if ( c.getCode().equals(code) ){
                    cname = c.getName();
                    break;
                }
            }
            
            System.out.println("  "+code+" "+cname+": "+grade);
            
        }
    }
    
    public void printStudentAttainments(String studentNumber){
        String name = "";
        for ( Student stud : students ){
            String opn = stud.getStudentNumber();
            if ( opn.equals(studentNumber) ){
                name = stud.getName();
                break;
            }
        }
        
        // no name found
        if ( name.equals("") ){
            System.out.println("Unknown student number: "+studentNumber);
            return;
        }

        // found courses
        ArrayList<Attainment> found = new ArrayList<>();
        System.out.println(name+" ("+studentNumber+"):");
        for ( Attainment att : attainments ) {
            if ( att.getStudentNumber().equals(studentNumber) ){
                found.add(att);
            }
        }
        
        // printing
        for ( Attainment att : found ) {
            String code = att.getCourseCode();
            String cname = "";
            int grade = att.getGrade();
            for ( Course c : courses){
                if ( c.getCode().equals(code) ){
                    cname = c.getName();
                    break;
                }
            }
            
            System.out.println("  "+code+" "+cname+": "+grade);
            
        }
    }
    
}
