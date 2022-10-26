package com.example.tutorialv3.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ScreenStateProperty extends Property<ScreenState> {
   private final HashMap<String, ScreenState> statesMap = new HashMap();

   protected ScreenStateProperty() {
      // only one
      super("cinema", ScreenState.class);
      for (Direction direction : Direction.values()) {
         for (int x = 0; x < 10; x ++) {
            for (int y = 0; y < 10; y ++) {
               ScreenState screenState = new ScreenState(direction, x, y);
               statesMap.put(screenState.toString(), screenState);
            }
         }
      }
   }

   public Collection<ScreenState> getPossibleValues() {
      return this.statesMap.values();
   }

   public static ScreenStateProperty create() {
      return new ScreenStateProperty();
   }

   public Optional<ScreenState> getValue(String screenState) {
      return Optional.ofNullable(this.statesMap.get(screenState));
   }

   public String getName(ScreenState screenState) {
      return screenState.toString();
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof ScreenStateProperty && super.equals(other)) {
         ScreenStateProperty property = (ScreenStateProperty) other;
         return this.statesMap.equals(property.statesMap);
      } else {
         return false;
      }
   }

   public int generateHashCode() {
      return 31 * super.generateHashCode() + this.statesMap.hashCode();
   }
}