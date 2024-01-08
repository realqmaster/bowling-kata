package it.euris.bowlingkata.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  @DisplayName("10 pins down in one roll is a strike")
  void if_10_pins_down_in_one_roll_then_it_is_a_strike() {
    Frame frame = new Frame(1);
    frame.roll(10);
    assertThat(frame.isStrike()).isTrue();
    assertThat(frame.isSpare()).isFalse();
  }

  @Test
  @DisplayName("10 pins down in two rolls is a spare")
  void if_10_pins_down_in_two_roll_then_it_is_a_spare() {
    Frame frame = new Frame(1);
    frame.roll(5);
    frame.roll(5);
    assertThat(frame.isSpare()).isTrue();
    assertThat(frame.isStrike()).isFalse();
  }

  @Test
  @DisplayName("Less than 10 pins down in two rolls is neither a strike nor a spare")
  void if_less_than_10_pins_down_in_two_roll_then_it_is_neither_a_strike_nor_a_spare() {
    Frame frame = new Frame(1);
    frame.roll(5);
    frame.roll(4);
    assertThat(frame.isSpare()).isFalse();
    assertThat(frame.isStrike()).isFalse();
    assertThat(frame.getRolls()).isEqualTo(2);
    assertThat(frame.totalPins()).isEqualTo(9);
    assertThat(frame.isComplete()).isTrue();
  }

  @Test
  @DisplayName("If not last frame, rolls beyond second are ignored")
  void if_not_last_frame_rolls_beyond_second_are_ignored() {
    Frame frame = new Frame(1);
    frame.roll(5);
    frame.roll(4);
    frame.roll(1);
    assertThat(frame.isSpare()).isFalse();
    assertThat(frame.isStrike()).isFalse();
    assertThat(frame.getRolls()).isEqualTo(2);
    assertThat(frame.totalPins()).isEqualTo(9);
    assertThat(frame.isComplete()).isTrue();
  }

  @Test
  @DisplayName("If not last frame, frame is complete after two rolls")
  void if_not_last_frame_frame_is_complete_after_two_rolls() {
    Frame frame = new Frame(1);
    frame.roll(5);
    frame.roll(4);
    assertThat(frame.isComplete()).isTrue();
  }

  @Test
  @DisplayName("If last frame, frame is complete after three rolls")
  void if_last_frame_frame_is_complete_after_three_rolls() {
    Frame frame = new Frame(10);
    frame.roll(1);
    frame.roll(1);
    frame.roll(1);
    assertThat(frame.isComplete()).isTrue();
  }

  @Test
  @DisplayName("If not last frame, frame is not complete after 1 non strike roll")
  void if_not_last_frame_frame_is_not_complete_after_1_non_strike_roll() {
    Frame frame = new Frame(1);
    frame.roll(5);
    assertThat(frame.isComplete()).isFalse();
  }

  @Test
  @DisplayName("If not last frame, frame is complete after 2 non strike rolls")
  void if_not_last_frame_frame_is_complete_after_2_non_strike_rolls() {
    Frame frame = new Frame(1);
    frame.roll(5);
    frame.roll(2);
    assertThat(frame.isComplete()).isTrue();
  }

  @Test
  @DisplayName("If last frame, up to 2 rolls allowed after 1 strike")
  void if_last_frame_frame_is_complete_after_3_non_strike_rolls() {
    Frame frame = new Frame(10);
    frame.roll(10);
    frame.roll(5);
    frame.roll(2);
    assertThat(frame.isComplete()).isTrue();
    assertThat(frame.getRolls()).isEqualTo(3);
    assertThat(frame.totalPins()).isEqualTo(17);
  }
}
