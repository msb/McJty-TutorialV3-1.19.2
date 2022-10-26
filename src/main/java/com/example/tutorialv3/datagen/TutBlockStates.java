package com.example.tutorialv3.datagen;

import com.example.tutorialv3.TutorialV3;
import com.example.tutorialv3.blocks.AnimatedOreBlock;
import com.example.tutorialv3.blocks.ScreenState;
import com.example.tutorialv3.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;

import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
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

        // We don't strictly need this default - but i'm leaving it in as an example.
        // Note that the order is important "iron_block" as the default must come before the conditional blocks.

        ModelFile iron = models().getExistingFile(mcLoc("block/iron_block"));
        stateBuilder.part().modelFile(iron).addModel();

        ModelFile terracotta = models().getExistingFile(mcLoc("block/terracotta"));
        ModelFile diorite = models().getExistingFile(mcLoc("block/diorite"));

        for (int x = 0; x < 10; x ++) {
            for (int y = 0; y < 10; y ++) {
                ModelFile modelFile;
                switch ((x + y) % 3) {
                    case 0:
                        modelFile = modelbuilder;
                        break;
                    case 1:
                        modelFile = terracotta;
                        break;
                    default:
                        modelFile = diorite;
                }
                stateBuilder.part().modelFile(modelFile).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.NORTH, x, y));
                stateBuilder.part().modelFile(modelFile).rotationY(90).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.EAST, x, y));
                stateBuilder.part().modelFile(modelFile).rotationY(180).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.SOUTH, x, y));
                stateBuilder.part().modelFile(modelFile).rotationY(270).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.WEST, x, y));
                stateBuilder.part().modelFile(modelFile).rotationX(90).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.DOWN, x, y));
                stateBuilder.part().modelFile(modelFile).rotationX(270).addModel().condition(AnimatedOreBlock.SCREEN, new ScreenState(Direction.UP, x, y));
            }
        }
    }
}
