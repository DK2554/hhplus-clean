package io.hhplus.cleanarchitecture.interfaces.user;


import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.infra.user.JpaUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestUserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Test
    @DisplayName("사용자 아이디가 존재하면 해당 사용자를 반환한다.")
    void shouldReturnUserWhenUserExists() {
        // Given
        Long userId = 1L;
        User userItem = new User().builder().id(userId).build();
        jpaUserRepository.save(userItem);  // 데이터를 저장

        // When

        Optional<User> foundUser = jpaUserRepository.findById(userId);  // 데이터 조회

        // Then
        assertThat(foundUser).isPresent();  // Optional이 값이 존재하는지 확인
        assertThat(foundUser.get().getId()).isEqualTo(userId);  // 아이디가 일치하는지 검증
    }
    @Test
    @DisplayName("사용자 아이디가 존재하지 않으면 빈 Optional을 반환한다.")
    void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
        // Given
        Long userId = 99L;

        // When
        Optional<User> foundUser = jpaUserRepository.findById(userId);  // 데이터 조회

        // Then
        assertThat(foundUser).isEmpty();  // 값이 없으면 빈 Optional을 반환해야 함
    }

}
