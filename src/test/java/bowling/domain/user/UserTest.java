package bowling.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.user.UsernameEnglishException;
import bowling.exception.user.UsernameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"영어", "123", "[]"})
    @DisplayName("유저 이름이 영어가 들어오지 않으면 Exception이 발생해야 한다.")
    void nameEnglishExceptionTest(String input) {

        // when & then
        assertThatExceptionOfType(UsernameEnglishException.class)
            .isThrownBy(() -> User.of(input))
            .withMessageMatching("유저의 이름은 영어만 들어와야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "testtest"})
    @DisplayName("유저 이름이 3보다 큰 길이로 들어오면 Exception이 발생해야 한다.")
    void nameLengthExceptionTest(String input) {

        // when & then
        assertThatExceptionOfType(UsernameLengthException.class)
            .isThrownBy(() -> User.of(input))
            .withMessageMatching("유저의 이름은 3글자 이하로 들어와야 합니다.");
    }

    @Test
    @DisplayName("User equals, hashCode 재정의 테스트")
    void userEqualsHashCodeTest() {

        // given & when
        User result = User.of("cjy");

        // then
        assertThat(result)
            .isEqualTo(User.of("cjy"))
            .hasSameHashCodeAs(User.of("cjy"));
    }

}