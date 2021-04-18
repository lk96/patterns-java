package org.kevin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDataMapperImpl implements StudentDataMapper {

    private final List<Student> students = new ArrayList<>();

    @Override
    public Optional<Student> find(int studentId) {
        return this.students.stream().filter(x -> x.getStudentId() == studentId).findFirst();
    }

    @Override
    public void insert(Student student) throws DataMapperException {
        Optional<Student> stu = find(student.getStudentId());
        if (stu.isPresent()) {
            String name = student.getName();
            throw new DataMapperException("student already [" + name + "] exists");
        }
        students.add(student);
    }

    @Override
    public void update(Student student) throws DataMapperException {
        String name = student.getName();
        Integer index = Optional.of(student)
                .map(Student::getStudentId)
                .flatMap(this::find)
                .map(students::indexOf)
                .orElseThrow(() -> new DataMapperException("student [" + name + "] is not found"));
        students.set(index, student);
    }

    @Override
    public void delete(Student student) throws DataMapperException {
        if (!students.remove(student)) {
            String name = student.getName();
            throw new DataMapperException("student [" + name + "] is not found");
        }
    }

    public List<Student> getStudents() {
        return this.students;
    }
}
