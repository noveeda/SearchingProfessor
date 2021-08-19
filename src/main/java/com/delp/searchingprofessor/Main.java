package com.delp.searchingprofessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Add SpringBootApplication Annotation
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        //SpringApplication.run(Class class, String...args);
        SpringApplication.run(Main.class, args);

        /*
        Parser parser = new Parser();
        parser.SearchTerm("엄경배");
        */
    }
}
