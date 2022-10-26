package com.example.tutorialv3.blocks;

import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ScreenState implements Comparable<ScreenState> {
    public final Direction direction;
    public final int x;
    public final int y;

    public ScreenState(@Nonnull Direction direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%s_%s_%s", direction, x, y);
    }

    @Override
    public int compareTo(@NotNull ScreenState o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenState that = (ScreenState) o;
        return x == that.x && y == that.y && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, x, y);
    }
}
