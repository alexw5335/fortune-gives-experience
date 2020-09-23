package com.huanfran.experiencemod;

import com.huanfran.experiencemod.config.Config;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FortuneGivesExperience.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {


    /**
     * Applies fortune enchantment scaling when mining ores.
     */
    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        if(event.getPlayer() == null) return;

        int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, event.getPlayer().getHeldItemMainhand());

        if(fortune > 0) event.setExpToDrop((int) Math.ceil(event.getExpToDrop() * (1 + fortune * Config.fortuneScalar)));
    }



    /**
     * Applies looting enchantment scaling when killing mobs.
     */
    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        if(event.getAttackingPlayer() == null) return;

        int looting = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, event.getAttackingPlayer().getHeldItemMainhand());

        if(looting > 0) event.setDroppedExperience((int) Math.ceil(event.getDroppedExperience() * (1 + looting * Config.lootingScalar)));
    }


}