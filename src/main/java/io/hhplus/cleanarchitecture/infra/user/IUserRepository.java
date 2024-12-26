package io.hhplus.cleanarchitecture.infra.user;

import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findByUserId(Long userId) {
        return jpaUserRepository.findById(userId).orElse(new User());
    }
}
