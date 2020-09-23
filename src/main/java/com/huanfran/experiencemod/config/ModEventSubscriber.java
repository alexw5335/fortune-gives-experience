package com.huanfran.experiencemod.config;

import com.huanfran.experiencemod.FortuneGivesExperience;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = FortuneGivesExperience.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {


    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == Config.CLIENT_SPEC) Config.bakeConfig();
    }


}
