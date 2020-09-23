package com.huanfran.experiencemod.config;

import com.huanfran.experiencemod.FortuneGivesExperience;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {


    public final ForgeConfigSpec.DoubleValue lootingScalar;
    public final ForgeConfigSpec.DoubleValue fortuneScalar;



    public ClientConfig(ForgeConfigSpec.Builder builder) {
        lootingScalar = builder
                .comment("The scaling factor for experience gained from weapons with the looting enchantment. 0.5 by default.")
                .translation(FortuneGivesExperience.MODID + ".config." + "Looting scalar")
                .defineInRange("Looting scalar", 0.5, 0, 1000);

        fortuneScalar = builder
                .comment("The scaling factor for experience gained from tools with the fortune enchantment. 0.5 by default.")
                .translation(FortuneGivesExperience.MODID + ".config." + "Fortune scalar")
                .defineInRange("Fortune scalar", 0.5, 0, 1000);
    }


}

