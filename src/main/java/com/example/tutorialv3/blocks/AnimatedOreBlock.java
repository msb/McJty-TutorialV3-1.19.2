package com.example.tutorialv3.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import static com.example.tutorialv3.setup.Registration.ORE_PROPERTIES;

/**
 * Simple block with `BlockStateProperties.FACING` set dependent on the placing player's facing
 * direction.
 */
public class AnimatedOreBlock extends Block {

    public static final ScreenStateProperty SCREEN = ScreenStateProperty.create();

    public static final Logger LOGGER = LogManager.getLogger();

    public AnimatedOreBlock() {
        super(ORE_PROPERTIES);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SCREEN);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
        BlockState bs = super.getStateForPlacement(context);
        ClientLevel level = Minecraft.getInstance().level;

        Direction facing = context.getNearestLookingDirection().getOpposite();
        BlockPos clickedPos = context.getClickedPos();

        for (Direction direction: Direction.values()) {
            BlockState adjoiningBlockState = level.getBlockState(clickedPos.relative(direction));
            String adjoiningBlockName = adjoiningBlockState.getBlock().getName().getString();
            if (adjoiningBlockName.equals("Screen")) {
                ScreenState adjoiningScreenState = adjoiningBlockState.getValue(SCREEN);
                // FIXME simplified
                ScreenState newScreenState = new ScreenState(adjoiningScreenState.direction,
                        adjoiningScreenState.x + (direction == Direction.DOWN ? 0 : 1),
                        adjoiningScreenState.y + (direction == Direction.DOWN ? 1 : 0)
                );
                // FIXME helper method on ScreenState
                if (newScreenState.x < 10 && newScreenState.y < 10) {
                    return bs.setValue(SCREEN, newScreenState);
                }
            }
        }
        return bs.setValue(SCREEN, new ScreenState(facing, 0, 0));
    }

    // found an implementation of this - keeping hold of it for now.
    public Direction getNearestLookingDirection() {
        LocalPlayer player = Minecraft.getInstance().player;
        Direction looking;
        if (player.getXRot() > 45) {
            looking = Direction.DOWN;
        } else if (player.getXRot() < -45) {
            looking = Direction.UP;
        } else {
            switch (mod(Math.round(player.getYRot() / 90), 4)) {
                case 0:
                    looking = Direction.SOUTH;
                    break;
                case 1:
                    looking = Direction.WEST;
                    break;
                case 2:
                    looking = Direction.NORTH;
                    break;
                default:
                    looking = Direction.EAST;
            }
        }
        return looking;
    }

    // A modulo method that works like python
    private int mod(double n, double base) {
        return (int) (n - (Math.floor(n / base)) * base);
    }
}
