package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

}
