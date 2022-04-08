package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @CollectionTable(name = "user_id")
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "User_Book",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )

    @Builder.Default
    private Set<BookEntity> bookEntitySet = new HashSet<>();

}
