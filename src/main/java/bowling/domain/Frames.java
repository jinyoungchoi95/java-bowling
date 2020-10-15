package bowling.domain;

import bowling.exception.GameOverException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {
    public static final int MAX_FRAME_COUNT = 10;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void pitch(int count) {
        if (isFinish()) {
            throw new GameOverException();
        }

        this.makeFrame();
        getLastFrame().pitch(count);
        calculateScore(count);
    }

    private void calculateScore(int count) {
        for (Frame frame : frames) {
            int index = frames.size() - 1;
            frame.calculateScore(index, count);
        }
    }

    private void makeFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.firstFrame());
            return;
        }

        if (!getLastFrame().isFinish()) {
            return;
        }

        frames.add(frames.size() > NormalFrame.MAX_FRAME_INDEX ? new FinalFrame() : getLastFrame().next());
    }


    private boolean isFinalFrame() {
        return frames.size() == MAX_FRAME_COUNT;
    }

    public boolean isFinish() {
        return isFinalFrame() && getLastFrame().isFinish();
    }

    private Frame getLastFrame() {
        if (frames.isEmpty()) {
            throw new GameOverException();
        }

        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int getIndex() {
        if (frames.isEmpty() || getLastFrame().isFinish()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public List<Integer> getScores() {
        return frames.stream()
                .filter(Frame::hasScore)
                .map(Frame::getScore)
                .collect(Collectors.toList());
    }

    public int size() {
        return frames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}