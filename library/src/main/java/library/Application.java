package library;

import library.entity.BookEntity;
import library.entity.UserEntity;
import library.repository.BookRepository;
import library.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userRepository.save(UserEntity.builder().id(1).name("John").build());
            userRepository.save(UserEntity.builder().id(2).name("Brian").build());

            bookRepository.save(BookEntity.builder().id(1).name("Harry Potter").build());
            bookRepository.save(BookEntity.builder().id(2).name("Harry Potter and chamber of secret").build());
        };
    }
}
