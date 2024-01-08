package it.euris.bowlingkata.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Game {

  private final List<Frame> frames = new ArrayList<>();
  private int frameNumber = 0;

  public void roll(int pins) {
    if ((frames.isEmpty() || frames.getLast().isComplete()) && frameNumber < 10) {
      frameNumber++;
      log.info("Creating new frame {}", frameNumber);
      Frame frame = new Frame(frameNumber);
      frames.add(frame);
    }
    log.info("Rolling on frame {}", frameNumber);
    frames.getLast().roll(pins);
  }

  public int score() {
    int score = 0;

    for (Frame frame : frames) {
      int current = frames.indexOf(frame);

      if (current < frames.size() - 1) {
        if (frame.isStrike()) {
          score += frames.get(current + 1).countLimitedTo(2);
        } else if (frame.isSpare()) {
          score += frames.get(current + 1).countLimitedTo(1);
        }
      }

      score += frame.totalPins();
    }

    return score;
  }
}
