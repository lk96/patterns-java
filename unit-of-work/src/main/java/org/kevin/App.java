package org.kevin;

import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) {
        var ram = new Student(1, "Ram", "Street 9, Cupertino");
        var shyam = new Student(2, "Shyam", "Z bridge, Pune");
        var gopi = new Student(3, "Gopi", "Street 10, Mumbai");

        var context = new HashMap<String, List<Student>>();
        var studentDatabase = new StudentDatabase();
        var studentRepository = new StudentRepository(context, studentDatabase);

        studentRepository.registerNew(ram);
        studentRepository.registerModified(shyam);
        studentRepository.registerDeleted(gopi);
        studentRepository.commit();
    }
}
