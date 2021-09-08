package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Post, Long> {

}
