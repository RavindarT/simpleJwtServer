package com.simpleJwt.server.SimpleJwtServer.reposMongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simpleJwt.server.SimpleJwtServer.reposMongoDB.data.Books;

@Repository
public interface BooksRepository extends MongoRepository<Books, String> {

}
