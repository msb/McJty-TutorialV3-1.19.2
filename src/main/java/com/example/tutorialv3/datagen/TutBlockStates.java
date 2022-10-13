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

        MultiPartBlockStateBuilder bld = getMultipartBuilder(Registration.MYSTERIOUS_ORE_OVERWORLD.get());

        // MYSTERIOUS_ORE_OVERWORLD is re-purposed as a screen.

        for (Direction theDirection : Direction.values()) {
            BlockModelBuilder builder = models().getBuilder("block/mysterious_ore_overworld/" + theDirection);
            builder.parent(models().getExistingFile(mcLoc("cube_all")));

            builder.texture("screen", modLoc("block/mysterious_ore_overworld"));
            builder.texture("back", mcLoc("block/iron_block"));

            builder.element()
                    .from(0, 0, 0)
                    .to(16, 16, 16)
                    .allFaces((direction, faceBuilder) -> faceBuilder.texture(direction == theDirection ? "#screen" : "#back"))
                    .end();

            // TODO it occurs to me that you could use `rotationX()/rotationY()` here instead of defining 6 models
            bld.part().modelFile(builder).addModel().condition(BlockStateProperties.FACING, theDirection);
        }

    }
}
