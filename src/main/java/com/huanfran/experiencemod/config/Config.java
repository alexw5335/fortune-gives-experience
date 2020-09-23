package com.huanfran.experiencemod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {


    public static final ClientConfig CLIENT;

    public static final ForgeConfigSpec CLIENT_SPEC;



    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }



    public static double lootingScalar;
    public static double fortuneScalar;



    public static void bakeConfig() {
        lootingScalar = CLIENT.lootingScalar.get();
        fortuneScalar = CLIENT.fortuneScalar.get();
    }


}
