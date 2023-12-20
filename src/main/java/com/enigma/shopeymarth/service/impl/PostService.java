package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.entity.Posts;
import com.enigma.shopeymarth.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${api.endpoint.url.post}")
    private String BASE_URL;

    private final RestTemplate restTemplate;
    private final PostsRepository postsRepository;

    public ResponseEntity<List<Posts>> getAllPosts() {
        ResponseEntity<Posts[]> responseEntity = restTemplate.getForEntity(BASE_URL, Posts[].class);
        List<Posts> listReponse = List.of(responseEntity.getBody());
        List<Posts> dbGetAllPost = postsRepository.findAll();
        dbGetAllPost.addAll(listReponse);

        listReponse.stream().map(posts ->
                dbGetAllPost.stream().map(posts1 ->
                        {
                            if (posts != posts1) {
                                postsRepository.save(posts);
                            }
                            return posts1;
                        }
                )
        );



        return ResponseEntity.ok(dbGetAllPost);
    }

    public ResponseEntity<String> getPostsByid(Long id) {


        return responseMethod(restTemplate.getForEntity(BASE_URL + id, String.class), "Failed get post by id");
    }

    public ResponseEntity<String> createPosts(Posts posts) {
        //save to database
        postsRepository.save(posts);
        //Mengatur header permintaan
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //Membungkus data perminataan dalam httpEntity
        HttpEntity<Posts> requestEntity = new HttpEntity<>(posts, httpHeaders);

        //Response
        return responseMethod(restTemplate.postForEntity(BASE_URL, requestEntity, String.class), "Failed to create posts");

    }

    private ResponseEntity<String> responseMethod(ResponseEntity<String> restTemplate, String message) {
        ResponseEntity<String> responseEntity = restTemplate;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            return ResponseEntity.ok(responseBody);
        }
        return ResponseEntity.status(responseEntity.getStatusCode()).body(message);
    }

}
