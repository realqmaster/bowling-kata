package it.simoni.bowlingkata.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {


  @Test
  @DisplayName("Three strikes should create 3 frames")
  void if_three_strikes_should_create_three_frames() {
    Game game = new Game();
    game.roll(10);
    game.roll(10);
    game.roll(10);
    assertThat(game.getFrames()).hasSize(3);
    assertThat(game.getFrameNumber()).isEqualTo(3);
  }

  @Test
  @DisplayName("Six non strike rolls should create 3 frames")
  void if_six_non_strike_rolls_should_create_three_frames() {
    Game game = new Game();
    game.roll(5);
    game.roll(4);
    game.roll(3);
    game.roll(2);
    game.roll(1);
    game.roll(0);
    assertThat(game.getFrames()).hasSize(3);
    assertThat(game.getFrameNumber()).isEqualTo(3);
  }

  @Test
  @DisplayName("At most 10 frames are created")
  void at_most_10_frames_are_created() {
    Game game = new Game();
    for (int i = 0; i < 30; i++) {
      game.roll(0);
    }
    assertThat(game.getFrames()).hasSize(10);
    assertThat(game.getFrameNumber()).isEqualTo(10);
    assertThat(game.getFrames()).allSatisfy(frame -> {
      assertThat(frame.isComplete()).isTrue();
      assertThat(frame.getRolls()).isEqualTo(2);
    });
  }

  @Test
  @DisplayName("A strike and two single points should yield 14 score")
  void if_strike_and_two_single_points_should_yield_14_score() {
    Game game = new Game();
    game.roll(10);
    game.roll(1);
    game.roll(1);
    assertThat(game.score()).isEqualTo(14);
  }

  @Test
  @DisplayName("A spare and two single points should yield 13 score")
  void if_spare_and_two_single_points_should_yield_13_score() {
    Game game = new Game();
    game.roll(5);
    game.roll(5);
    game.roll(1);
    game.roll(1);
    assertThat(game.score()).isEqualTo(13);
  }

  @Test
  @DisplayName("Last frame should allow 3 rolls on strike")
  void last_frame_should_allow_3_rolls_on_strike() {
    Game game = new Game();
    for (int i = 0; i < 18; i++) {
      game.roll(1);
    }
    game.roll(10);
    game.roll(10);
    game.roll(10);

    assertThat(game.score()).isEqualTo(48);
  }

  @Test
  @DisplayName("Last frame should allow 3 rolls on spare")
  void last_frame_should_allow_3_rolls_on_spare() {
    Game game = new Game();
    for (int i = 0; i < 18; i++) {
      game.roll(1);
    }
    game.roll(5);
    game.roll(5);
    game.roll(5);

    assertThat(game.score()).isEqualTo(33);
  }
}
