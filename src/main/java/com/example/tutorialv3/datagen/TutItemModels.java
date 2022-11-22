package com.example.tutorialv3.datagen;

import com.example.tutorialv3.TutorialV3;
import com.example.tutorialv3.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TutorialV3.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // MSB: `get().getRegistryName()` replaced by `getId()`
        withExistingParent(Registration.MYSTERIOUS_ORE_OVERWORLD_ITEM.getId().getPath(), modLoc("block/mysterious_ore_overworld"));
        withExistingParent(Registration.MYSTERIOUS_ORE_NETHER_ITEM.getId().getPath(), modLoc("block/mysterious_ore_nether"));
        withExistingParent(Registration.MYSTERIOUS_ORE_END_ITEM.getId().getPath(), modLoc("block/mysterious_ore_end"));
        withExistingParent(Registration.MYSTERIOUS_ORE_DEEPSLATE_ITEM.getId().getPath(), modLoc("block/mysterious_ore_deepslate"));

        singleTexture(Registration.RAW_MYSTERIOUS_CHUNK.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_mysterious_chunk"));
        singleTexture(Registration.MYSTERIOUS_INGOT.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/mysterious_ingot"));
    }
}
