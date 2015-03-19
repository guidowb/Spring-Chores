package org.guidowb.chores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.guidowb")
public class SpringChoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringChoresApplication.class, args);
    }
}
