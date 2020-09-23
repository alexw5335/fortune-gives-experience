package com.huanfran.experiencemod;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "fortune-gives-experience", name = "Fortune Gives Experience", version = "1.0.0")
@Config(modid = "fortune-gives-experience")
@Mod.EventBusSubscriber(modid = "fortune-gives-experience")
public class FortuneGivesExperience {



    @Config.Comment("The xp scalar for a tool's fortune level. 0.5 by default.")
    public static double fortuneScalar = 0.5;

    @Config.Comment("The xp scalar for a weapon's looting level. 0.5 by default.")
    public static double lootingScalar = 0.5;




    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        if(event.getPlayer() == null) return;

        int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, event.getPlayer().getHeldItemMainhand());

        if(fortune > 0) event.setExpToDrop((int) Math.ceil(event.getExpToDrop() * (1 + fortune * fortuneScalar)));
    }




    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        if(event.getAttackingPlayer() == null) return;

        int looting = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, event.getAttackingPlayer().getHeldItemMainhand());

        if(looting > 0) event.setDroppedExperience((int) Math.ceil(event.getDroppedExperience() * (1 + looting * lootingScalar)));
    }


}