package bowling.domain.frame;

import bowling.domain.score.Score;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;
import static bowling.domain.score.Score.ordinary;

public class Ordinary implements State {
    private final int score;
    private int leftTry = MIN_LEFT_TRY;

    public Ordinary(int score) {
        this.score = score;
    }

    public Ordinary(int score, int leftTry) {
        this.score = score;
        this.leftTry = leftTry;
    }

    @Override
    public State record(int score) {
        if (this.score + score == MAX_SCORE) {
            return recordSpare(score);
        }
        if (score == MIN_SCORE) {
            return new LastGutter();
        }
        return new LastOrdinary(score);
    }

    private State recordSpare(int score) {
        if (leftTry == MIN_LEFT_TRY) {
            return new LastSpare(score);
        }
        return new Spare(score);
    }

    @Override
    public Score getScore() {
        return ordinary(score);
    }
}
