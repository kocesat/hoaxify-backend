package com.hoaxify.backend.library.model;

import com.hoaxify.backend.library.enums.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Table(name = "author_role", schema = "hoaxify")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Role role;
    private Long authorId;

    public static AuthorRole newInstance(Long authorId, int roleCode) {
        AuthorRole.AuthorRoleBuilder builder = AuthorRole.builder();
        return builder
                .authorId(authorId)
                .role(Role.getFromCode(roleCode))
                .build();
    }

}
