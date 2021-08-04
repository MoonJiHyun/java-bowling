package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Collections;
import java.util.List;

public class Preparation extends State {

    private Preparation() {}

    public static Preparation init() {
        return new Preparation();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        if (downedPins.isAllDown()) {
            return Strike.init();
        }

        return InProgress.from(downedPins);
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.emptyList();
    }

    @Override
    public boolean isStart() {
        return true;
    }
}