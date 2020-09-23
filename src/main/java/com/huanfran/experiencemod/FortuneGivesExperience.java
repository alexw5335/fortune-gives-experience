package com.huanfran.experiencemod;

import com.huanfran.experiencemod.config.Config;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

/**
 * A mod that allows the fortune and looting enchantments to grant additional experience to the player when mining and
 * killing mobs.
 */
@Mod(FortuneGivesExperience.MODID)
public class FortuneGivesExperience {


    public static final String MODID = "fortune-gives-experience";


    public FortuneGivesExperience() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }


}