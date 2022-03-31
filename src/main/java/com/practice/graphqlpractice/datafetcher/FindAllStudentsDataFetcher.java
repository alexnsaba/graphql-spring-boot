package com.practice.graphqlpractice.datafetcher;

import com.practice.graphqlpractice.entity.StudentEntity;
import com.practice.graphqlpractice.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FindAllStudentsDataFetcher implements DataFetcher<List<StudentEntity>> {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return studentRepository.findAll();
    }
}
