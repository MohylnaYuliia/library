package library.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class BookEntity {

    @Id
    private Integer id;

    private String name;

    private Integer copyCount;
}
