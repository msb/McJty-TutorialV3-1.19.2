package com.example.tutorialv3.datagen;

import com.example.tutorialv3.TutorialV3;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialV3.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        boolean run = true;
        if (event.includeServer()) {
            generator.addProvider(run, new TutRecipes(generator));
            generator.addProvider(run, new TutLootTables(generator));
            TutBlockTags blockTags = new TutBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(run, blockTags);
            generator.addProvider(run, new TutItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(run, new TutBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(run, new TutItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(run, new TutLanguageProvider(generator, "en_us"));
        }
    }
}
