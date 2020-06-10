package com.simpleJwt.server.SimpleJwtServer.reposStgDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpleJwt.server.SimpleJwtServer.reposStgDB.data.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
