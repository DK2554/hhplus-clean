package io.hhplus.cleanarchitecture.infra.user;

import io.hhplus.cleanarchitecture.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
