package bowling.domain.frame;

import bowling.domain.status.Status;
import bowling.domain.status.Strike;

public class NormalFrame implements Frame {
    private static final int FIRST_TRY = 0;
    private static final int MAX_TRY_COUNT = 2;

    private Pins pins;
    private Status status;
    private int trying;

    public NormalFrame(Pins pins, int trying) {
        this.pins = pins;
        this.trying = trying;
    }

    public static Frame init() {
        return new NormalFrame(Pins.init(), 0);
    }


    public void bowl(int downPin) {
        status = bowling(downPin);
        trying = addTrying();
    }

    @Override
    public Frame next() {
        return new NormalFrame(Pins.init(), 0);
    }

    @Override
    public boolean isLastTryAtFrame() {
        return trying == MAX_TRY_COUNT;
    }

    public String printFrameResult() {
        if (trying == 1) {
            return status.printResult();
        }
        return status.printAllResult();
    }

    private Status bowling(int downPin) {
        if (trying == FIRST_TRY) {
            return pins.firstBowl(downPin);
        }
        return pins.bowl(downPin);
    }

    private int addTrying() {
        if (status instanceof Strike) {
            return MAX_TRY_COUNT;
        }
        return trying + 1;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "pins=" + pins.getDownPin() +
                ", status=" + printFrameResult() +
                ", trying=" + trying +
                '}';
    }
}