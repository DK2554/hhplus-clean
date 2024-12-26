package io.hhplus.cleanarchitecture.domain.user;

public interface UserRepository {
    User findByUserId(Long userId);
}
