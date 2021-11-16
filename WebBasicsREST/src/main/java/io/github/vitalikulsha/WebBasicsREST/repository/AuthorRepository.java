package io.github.vitalikulsha.WebBasicsREST.repository;

import io.github.vitalikulsha.WebBasicsREST.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);
}
