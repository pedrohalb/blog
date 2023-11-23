package br.com.unifalmg.blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db", name = "user")
public class User implements Serializable {

    @Id
    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;
}
