package com.showcase.api.domain.model;

import com.showcase.api.domain.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated
	@Column(nullable = false)
	private UserRole role = UserRole.USER;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(nullable = false)
	private String password;

	@Email(message = "email is not valid")
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private boolean active;

	@CreatedDate
	@Column
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column
	private LocalDateTime updatedAt;

}
