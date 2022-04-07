package library;

import library.entity.UserEntity;
import library.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository repository;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            repository.save(new UserEntity(1, "John"));
            repository.save(new UserEntity(2, "Brian"));
        };
    }
}
