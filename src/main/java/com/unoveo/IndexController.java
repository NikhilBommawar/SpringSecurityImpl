package com.unoveo;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping("/demo")
    public String calculate(Authentication authenticate) throws IOException, InterruptedException {

                            //        HttpClient httpClient =  HttpClient.newHttpClient();
                            //        HttpRequest request = HttpRequest.newBuilder()
                            //                .uri(URI.create("https://www.google.com"))
                            //                .build();
                            //
                            //       HttpResponse response =  httpClient.send(request, HttpResponse.BodyHandlers.discarding());


      return "demoSecurity";
    }

    @RequestMapping("/accessDenied")
    public String acessDeniedHandler() {
        return "accessDenied";
    }


    @RequestMapping("/afterLogin")
    public void redirectToAngular(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter pw = response.getWriter();
        response.sendRedirect("http://localhost:8080/angular/");
        pw.close();
    }

}