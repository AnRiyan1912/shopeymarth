package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.entity.Posts;
import com.enigma.shopeymarth.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    private final PostService postService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Posts>> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostById(@PathVariable long id) {
        return postService.getPostsByid(id);
    }

    @PostMapping()
    public ResponseEntity<String> createPosts(@RequestBody Posts posts) {
        return postService.createPosts(posts);
    }
}
