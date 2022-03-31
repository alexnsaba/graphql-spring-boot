package com.practice.graphqlpractice.datafetcher;

import com.practice.graphqlpractice.entity.StudentEntity;
import com.practice.graphqlpractice.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudentDataFetcher implements DataFetcher<StudentEntity> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentEntity get(DataFetchingEnvironment dataFetchingEnvironment){

        int studentId = dataFetchingEnvironment.getArgument("studentId");
        return studentRepository.findById(Long.valueOf(studentId)).get();
    }
}
