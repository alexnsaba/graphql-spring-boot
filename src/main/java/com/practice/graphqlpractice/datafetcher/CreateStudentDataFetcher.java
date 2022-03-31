package com.practice.graphqlpractice.datafetcher;

import com.practice.graphqlpractice.entity.StudentEntity;
import com.practice.graphqlpractice.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateStudentDataFetcher implements DataFetcher<StudentEntity> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentEntity get(DataFetchingEnvironment dataFetchingEnvironment) {
        StudentEntity student = new StudentEntity();

        student.setStudentName(dataFetchingEnvironment.getArgument("studentName"));
        student.setStudentNumber(dataFetchingEnvironment.getArgument("studentNumber"));
        student.setCourse(dataFetchingEnvironment.getArgument("course"));
        student.setYearOfStudy(dataFetchingEnvironment.getArgument("yearOfStudy"));

        return studentRepository.save(student);
    }
}
