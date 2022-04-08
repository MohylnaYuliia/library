package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @Column(name = "book_id")
    private Integer id;

    private String name;

    private boolean existed;

    @ManyToMany(mappedBy = "bookEntitySet")
    private Set<UserEntity> userEntities;

}
