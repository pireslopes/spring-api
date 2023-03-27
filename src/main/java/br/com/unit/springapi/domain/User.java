package br.com.unit.springapi.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
