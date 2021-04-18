package org.kevin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class StudentRepository implements IUnitOfWork<Student> {

    private final Map<String, List<Student>> context;
    private final StudentDatabase studentDatabase;

    @Override
    public void registerNew(Student student) {
        log.info("Registering {} for insert in context.", student.getName());
        register(student, UnitActions.INSERT.getActionValue());
    }

    @Override
    public void registerModified(Student student) {
        log.info("Registering {} for modify in context.", student.getName());
        register(student, UnitActions.MODIFY.getActionValue());

    }

    @Override
    public void registerDeleted(Student student) {
        log.info("Registering {} for delete in context.", student.getName());
        register(student, UnitActions.DELETE.getActionValue());
    }

    private void register(Student student, String operation) {
        var studentsToOperate = context.get(operation);
        if (studentsToOperate == null) {
            studentsToOperate = new ArrayList<>();
        }
        studentsToOperate.add(student);
        context.put(operation, studentsToOperate);
    }

    /**
     * All UnitOfWork operations are batched and executed together on commit only.
     */
    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
            return;
        }
        log.info("Commit started");
        if (context.containsKey(UnitActions.INSERT.getActionValue())) {
            commitInsert();
        }

        if (context.containsKey(UnitActions.MODIFY.getActionValue())) {
            commitModify();
        }
        if (context.containsKey(UnitActions.DELETE.getActionValue())) {
            commitDelete();
        }
        log.info("Commit finished.");
    }

    private void commitInsert() {
        var studentsToBeInserted = context.get(UnitActions.INSERT.getActionValue());
        for (var student : studentsToBeInserted) {
            log.info("Saving {} to database.", student.getName());
            studentDatabase.insert(student);
        }
    }

    private void commitModify() {
        var modifiedStudents = context.get(UnitActions.MODIFY.getActionValue());
        for (var student : modifiedStudents) {
            log.info("Modifying {} to database.", student.getName());
            studentDatabase.modify(student);
        }
    }

    private void commitDelete() {
        var deletedStudents = context.get(UnitActions.DELETE.getActionValue());
        for (var student : deletedStudents) {
            log.info("Deleting {} to database.", student.getName());
            studentDatabase.delete(student);
        }
    }
}