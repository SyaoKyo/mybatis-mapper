package me.syaokyo.mapper;

import me.syaokyo.entity.Student;

import java.util.List;

/**
 * Created by SyaoKyo on 2018/9/4.
 */
public interface StudentMapper {

    Student selectStuById(String stuNo);

    List<Student> selectByFirstName(String firstName);

    void addStudent(Student s);

    void deleteStudentById(String stuNo);

    void updateStudentById(Student s);
}
