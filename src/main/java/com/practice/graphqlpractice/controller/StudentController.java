package com.practice.graphqlpractice.controller;

import com.practice.graphqlpractice.service.GraphqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private GraphqlService graphqlService;

    @PostMapping
    public ResponseEntity<?> studentOperations(@RequestBody String query){
        return ResponseEntity.status(HttpStatus.OK).body(graphqlService.graphQL.execute(query));
    }
}
