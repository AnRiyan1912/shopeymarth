package com.enigma.shopeymarth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello() {
        return "<h1>Hello world</h1>";
    }

    @GetMapping(path = "/hobby")
    public String[] hobby() {
        return new String[]{"Makan", "Tidur"};
    }

    @GetMapping(path = "/search{key}")
    public String getRequestParams(@RequestParam String key) {
        String[] res = {"<h1>", "</h1>"};
        return res[0] + key + res[1];
    }

    @GetMapping("/data/{id}/{title}/location{batam}")
    public String getDataById(@PathVariable String id, @PathVariable String title, @RequestParam String key) {
        return "Data: " + id + " ini title: " + title + " location: " + "Batam";
    }
    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable String id) {
        String profile1 = "<h1 style=color:blue>Name: Andre Riyanto</h1> \n <p>Birth Date: 2004-12-19</p>";
        String profile2 = "<h1 style=color:blue>Name: Riyanto</h1> \n <p>Birth Date: 2004-10-19</p>";
        String profile3 = "<h1 style=color:blue>Name: Jamal Riyanto</h1> \n <p>Birth Date: 2004-10-22</p>";
        switch (id) {
            case "1":
                return profile1;
            case "2":
                return profile2;
            case "3":
                return profile3;
        }
      return "<h1 style>Profile not found!</h1>";
    }
}
