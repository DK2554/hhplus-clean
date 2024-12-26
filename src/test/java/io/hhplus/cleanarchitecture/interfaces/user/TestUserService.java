package io.hhplus.cleanarchitecture.interfaces.user;

import io.hhplus.cleanarchitecture.domain.user.User;
import io.hhplus.cleanarchitecture.domain.user.UserRepository;
import io.hhplus.cleanarchitecture.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("사용자 아이디 1L 요청시 해당 사용자를 반환한다.")
    void shouldGetUser() {
        Long userId = 1L;
        // Given
        User userItem = new User().builder().id(1L).build();

        when(userRepository.findByUserId(userId))
                .thenReturn(userItem);

        // When
        User user = userService.findByUserId(userId);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(userId);
        verify(userRepository, times(1)).findByUserId(userId);
    }

    @Test
    @DisplayName("사용자 아이디가 일치하는 사용자가 없으면 빈 객체를 반환한다.")
    void shouldThrowUserNotFoundExceptionWhenUser() {
        Long userId = 99L;
        // Given
        when(userRepository.findByUserId(userId))
                .thenReturn(new User());
        // When
        User user = userService.findByUserId(userId);

        assertThat(user).isNotNull();  // user가 null이 아님을 검증
        assertThat(user.getId()).isEqualTo(null);  // 빈 객체에 id는 null임

        verify(userRepository, times(1)).findByUserId(userId);  // repository 호출 여부 검증
    }


}
