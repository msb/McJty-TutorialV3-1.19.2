package com.example.tutorialv3.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
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

    public static final Logger LOGGER = LogManager.getLogger();

    public AnimatedOreBlock() {
        super(ORE_PROPERTIES);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
        BlockState bs = super.getStateForPlacement(context);
        LocalPlayer player = Minecraft.getInstance().player;
        Direction facing;
        if (player.getXRot() > 45) {
            facing = Direction.UP;
        } else if (player.getXRot() < -45) {
            facing = Direction.DOWN;
        } else {
            switch (mod(Math.round(player.getYRot() / 90), 4)) {
                case 0:
                    facing = Direction.NORTH;
                    break;
                case 1:
                    facing = Direction.EAST;
                    break;
                case 2:
                    facing = Direction.SOUTH;
                    break;
                default:
                    facing = Direction.WEST;
            }
        }
        LOGGER.info("facing: " + facing);
        return bs.setValue(BlockStateProperties.FACING, facing);
    }

    // A modulo method that works like python
    private int mod(double n, double base) {
        return (int) (n - (Math.floor(n / base)) * base);
    }
}
