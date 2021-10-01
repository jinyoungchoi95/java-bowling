package bowling.domain.state.running;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.exception.state.RunningCreateScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    @DisplayName("첫번째 볼링은 종료가 되지 않아야 한다.")
    void isFinishedFalseTest() {

        // given
        State state = new FirstBowl(Pin.of(5));

        // when
        boolean result = state.isFinished();

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("First Bowl상태에서 Score 생성하려고하면 exception이 발생해야 한다.")
    void createScoreExceptionTest() {

        // given
        State state = new FirstBowl(Pin.of(5));

        // when & then
        assertThatExceptionOfType(RunningCreateScoreException.class)
            .isThrownBy(() -> state.createScore())
            .withMessageMatching("running 상태는 score를 반환할 수 없습니다.");
    }

}