package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
public class UserEntity {

    @Id
    private Integer id;

    private String name;

    public UserEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
