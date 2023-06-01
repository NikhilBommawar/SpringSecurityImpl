package com.unoveo;


import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Controller for "/".
 *
 * @author Rob WInch
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/calc")
    public String calculate(Authentication authenticate) throws IOException, InterruptedException {

                            //        HttpClient httpClient =  HttpClient.newHttpClient();
                            //        HttpRequest request = HttpRequest.newBuilder()
                            //                .uri(URI.create("https://www.google.com"))
                            //                .build();
                            //
                            //       HttpResponse response =  httpClient.send(request, HttpResponse.BodyHandlers.discarding());


      return "calculate";
    }

    @RequestMapping("/accessDenied")
    public String acessDeniedHandler() {
        return "accessDenied";
    }

}