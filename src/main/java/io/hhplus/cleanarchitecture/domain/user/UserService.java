package io.hhplus.cleanarchitecture.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
