package it.simoni.bowlingkata.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Frame {

  private final int frameNumber;
  private int allowedRolls = 2;

  private List<Integer> pins;
  private int rolls = 0;

  public Frame(int frameNumber) {
    this.frameNumber = frameNumber;
    pins = new ArrayList<>();
  }

  public void roll(int pins) {
    if (isLastFrame() && (isSpare() || isStrike())) {
      allowedRolls = 3;
    }

    if (!isComplete()) {
      rolls++;
      this.pins.add(pins);
    }
  }

  public boolean isStrike() {
    return totalPins() == 10 && rolls == 1;
  }

  public boolean isSpare() {
    return totalPins() == 10 && rolls == 2;
  }

  public boolean isComplete() {
    return rolls == allowedRolls || (totalPins() == 10 && !isLastFrame());
  }

  public int totalPins() {
    return pins.stream().reduce(0, Integer::sum);
  }

  public int countLimitedTo(int limit) {
    return pins.stream().limit(limit).reduce(0, Integer::sum);
  }

  private boolean isLastFrame() {
    return frameNumber == 10;
  }
}
