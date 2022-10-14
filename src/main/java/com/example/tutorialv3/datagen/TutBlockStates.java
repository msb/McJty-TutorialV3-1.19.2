package com.example.tutorialv3.datagen;

import com.example.tutorialv3.TutorialV3;
import com.example.tutorialv3.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, TutorialV3.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.MYSTERIOUS_ORE_NETHER.get());
        simpleBlock(Registration.MYSTERIOUS_ORE_END.get());
        simpleBlock(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());

        MultiPartBlockStateBuilder stateBuilder = getMultipartBuilder(Registration.MYSTERIOUS_ORE_OVERWORLD.get());

        // MYSTERIOUS_ORE_OVERWORLD is re-purposed as a screen.

        BlockModelBuilder modelbuilder = models().getBuilder("block/mysterious_ore_overworld");
        modelbuilder.parent(models().getExistingFile(mcLoc("cube_all")));

        modelbuilder.texture("screen", modLoc("block/mysterious_ore_overworld"));
        modelbuilder.texture("back", mcLoc("block/iron_block"));

        modelbuilder.element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture(direction == Direction.NORTH ? "#screen" : "#back"))
                .end();

        stateBuilder.part().modelFile(modelbuilder).addModel().condition(BlockStateProperties.FACING, Direction.NORTH);
        stateBuilder.part().modelFile(modelbuilder).rotationY(90).addModel().condition(BlockStateProperties.FACING, Direction.EAST);
        stateBuilder.part().modelFile(modelbuilder).rotationY(180).addModel().condition(BlockStateProperties.FACING, Direction.SOUTH);
        stateBuilder.part().modelFile(modelbuilder).rotationY(270).addModel().condition(BlockStateProperties.FACING, Direction.WEST);
        stateBuilder.part().modelFile(modelbuilder).rotationX(90).addModel().condition(BlockStateProperties.FACING, Direction.DOWN);
        stateBuilder.part().modelFile(modelbuilder).rotationX(270).addModel().condition(BlockStateProperties.FACING, Direction.UP);
    }
}
