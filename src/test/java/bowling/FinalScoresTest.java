package bowling;

import bowling.domain.frame.FinalScores;
import bowling.domain.frame.Score;
import bowling.domain.frame.Scores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FinalScoresTest {


    private static Stream<Arguments> addScore_3번_던지기() {
        return Stream.of(

                arguments(1, 9, 3, new FinalScores(Arrays.asList(Score.ONE, Score.SPARE, Score.THREE))),
                arguments(2, 8, 5, new FinalScores(Arrays.asList(Score.TWO, Score.SPARE, Score.FIVE))),
                arguments(5, 5, 9, new FinalScores(Arrays.asList(Score.FIVE, Score.SPARE, Score.NINE))),
                arguments(6, 4, 10, new FinalScores(Arrays.asList(Score.SIX, Score.SPARE, Score.STRIKE))),
                arguments(9, 1, 1, new FinalScores(Arrays.asList(Score.NINE, Score.SPARE, Score.ONE))),
                arguments(9, 1, 0, new FinalScores(Arrays.asList(Score.NINE, Score.SPARE, Score.GUTTER))),
                arguments(10, 1, 9, new FinalScores(Arrays.asList(Score.STRIKE, Score.ONE, Score.SPARE))),
                arguments(10, 10, 10, new FinalScores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.STRIKE))),
                arguments(10, 10, 1, new FinalScores(Arrays.asList(Score.STRIKE, Score.STRIKE, Score.ONE)))
        );
    }

    @ParameterizedTest
    @MethodSource("addScore_3번_던지기")
    public void addScore_3번_던지기(int score1, int score2, int score3, Scores expectScores) throws Exception {
        // given
        Scores finalScores = new FinalScores();

        // when
        finalScores.addScore(score1);
        finalScores.addScore(score2);
        finalScores.addScore(score3);

        // then
        System.out.println(finalScores);
        assertThat(finalScores).isEqualTo(expectScores);
    }


    private static Stream<Arguments> FinalFrame_프레임이_끝난_경우_2번_던지기() {
        return Stream.of(
                arguments(0, 5),
                arguments(3, 4),
                arguments(4, 3),
                arguments(7, 1),
                arguments(8, 1)

        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝난_경우_2번_던지기")
    public void FinalFrame_프레임이_끝난_경우_2번_던지기(int score1, int score2) throws Exception {
        // given
        Scores finalScores = new FinalScores();

        // when
        finalScores.addScore(score1);
        finalScores.addScore(score2);

        // then
        assertThat(finalScores.isFinished()).isTrue();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝난_경우_3번_던지기() {
        return Stream.of(
                arguments(1, 9, 3),
                arguments(2, 8, 10),
                arguments(5, 5, 0),
                arguments(6, 4, 2),
                arguments(9, 1, 5),
                arguments(10, 10, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝난_경우_3번_던지기")
    public void FinalFrame_프레임이_끝난_경우_3번_던지기(int score1, int score2, int score3) throws Exception {
        // given
        Scores finalScores = new FinalScores();

        // when
        finalScores.addScore(score1);
        finalScores.addScore(score2);
        finalScores.addScore(score3);

        // then
        assertThat(finalScores.isFinished()).isTrue();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝나지_않은_경우_2번_던지기() {
        return Stream.of(
                arguments(1, 9),
                arguments(2, 8),
                arguments(5, 5),
                arguments(6, 4),
                arguments(9, 1),
                arguments(10, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝나지_않은_경우_2번_던지기")
    public void FinalFrame_프레임이_끝나지_않은_경우_2번_던지기(int score1, int score2) throws Exception {
        // given
        Scores finalScores = new FinalScores();

        // when
        finalScores.addScore(score1);
        finalScores.addScore(score2);

        // then
        assertThat(finalScores.isFinished()).isFalse();
    }

    private static Stream<Arguments> FinalFrame_프레임이_끝나지_않은_경우_1번_던지기() {
        return Stream.of(
                arguments(0),
                arguments(1),
                arguments(2),
                arguments(3),
                arguments(4),
                arguments(5),
                arguments(6),
                arguments(7),
                arguments(8),
                arguments(9),
                arguments(10)
        );
    }

    @ParameterizedTest
    @MethodSource("FinalFrame_프레임이_끝나지_않은_경우_1번_던지기")
    public void FinalFrame_프레임이_끝나지_않은_경우_1번_던지기(int score1) throws Exception {
        // given
        Scores finalScores = new FinalScores();

        // when
        finalScores.addScore(score1);

        // then
        assertThat(finalScores.isFinished()).isFalse();
    }


}
