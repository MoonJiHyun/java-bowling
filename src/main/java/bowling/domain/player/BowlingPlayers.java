package bowling.domain.player;

import bowling.dto.BowlingPlayerDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingPlayers {
    private static final int IDX_OF_FIRST_PLAYER = 0;

    private final List<BowlingPlayer> bowlingPlayers;

    public BowlingPlayers(List<String> names) {
        validate(names);

        bowlingPlayers = names.stream()
                .map(BowlingPlayer::from)
                .collect(Collectors.toList());
    }

    private void validate(List<String> names) {
        if (Objects.isNull(names)) {
            throw new IllegalArgumentException("Names list parameter can't be null");
        }
    }

    public static BowlingPlayers init(List<String> names) {
        return new BowlingPlayers(names);
    }

    public BowlingPlayer firstPlayer() {
        return bowlingPlayers.get(IDX_OF_FIRST_PLAYER);
    }

    public BowlingPlayer rotatePlayer(BowlingPlayer currentPlayer) {
        int indexOfCurrentPlayer = bowlingPlayers.indexOf(currentPlayer);
        int indexOfNextPlayer = (indexOfCurrentPlayer + 1) % bowlingPlayers.size();

        return bowlingPlayers.get(indexOfNextPlayer);
    }

    public List<BowlingPlayerDto> playerDtos() {
        return bowlingPlayers.stream()
                .map(BowlingPlayerDto::from)
                .collect(Collectors.toList());
    }

    public boolean isAllPlayersEnd() {
        return bowlingPlayers.stream()
                .allMatch(BowlingPlayer::isBowlingEnd);
    }
}