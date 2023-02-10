package sd.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CenterQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterQuizApplication.class, args);
        System.out.println(
                new BCryptPasswordEncoder().encode(
                        "Senha@123"
                )
        );
    }

}
