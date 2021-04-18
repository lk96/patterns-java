package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class App {

    private static final String STUDENT_STRING = "App.main(),student : ";

    public static void main(String[] args) {
        final StudentDataMapper mapper = new StudentDataMapperImpl();
        Student student = new Student(1, "adam", 'A');
        mapper.insert(student);
        log.info(STUDENT_STRING + student + "is inserted");

        Optional<Student> findStu = mapper.find(student.getStudentId());
        log.info(STUDENT_STRING + findStu + "is searched");

        student =new Student(student.getStudentId(),"adamUpdate",'A');
        mapper.update(student);

        log.info(STUDENT_STRING + student + "is updated");
        log.info(STUDENT_STRING + student + "is going to be deleted");
        mapper.delete(student);
    }


    
}
