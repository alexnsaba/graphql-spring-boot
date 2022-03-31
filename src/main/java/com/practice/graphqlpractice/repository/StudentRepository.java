package com.practice.graphqlpractice.repository;

import com.practice.graphqlpractice.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
