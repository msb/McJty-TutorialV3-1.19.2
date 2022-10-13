package com.example.tutorialv3.datagen;

import com.example.tutorialv3.TutorialV3;
import com.example.tutorialv3.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
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

        // MYSTERIOUS_ORE_OVERWORLD is re-purposed as a screen.
        // TODO currently it can only face one way.
        BlockModelBuilder builder = models().getBuilder("block/mysterious_ore_overworld");
        builder.parent(models().getExistingFile(mcLoc("cube_all")));

        builder.texture("screen", modLoc("block/mysterious_ore_overworld"));
        builder.texture("back", mcLoc("block/iron_block"));

        builder.element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture(direction == Direction.EAST ? "#screen" : "#back"))
                .end();
    }
}
