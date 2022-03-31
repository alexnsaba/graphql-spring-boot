package com.practice.graphqlpractice.service;

import com.practice.graphqlpractice.datafetcher.CreateStudentDataFetcher;
import com.practice.graphqlpractice.datafetcher.FindAllStudentsDataFetcher;
import com.practice.graphqlpractice.datafetcher.FindStudentDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphqlService {
    @Autowired
    private FindStudentDataFetcher findStudentDataFetcher;

    @Autowired
    private FindAllStudentsDataFetcher findAllStudentsDataFetcher;

    @Autowired
    private CreateStudentDataFetcher createStudentDataFetcher;

    @Value("classpath:schema.graphql")
    Resource schemaResource;

    public GraphQL graphQL;

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = generateRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring generateRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring().
                type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findAllStudents",findAllStudentsDataFetcher)).
                type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findStudent",findStudentDataFetcher)).
                type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createStudent",createStudentDataFetcher)).
                build();
    }
}
