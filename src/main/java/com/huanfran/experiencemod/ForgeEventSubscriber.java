package com.huanfran.experiencemod;

import com.huanfran.experiencemod.config.Config;
import com.huanfran.experiencemod.FortuneGivesExperience;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;
import net.minecraftforge.fml.loading.FMLServerLaunchProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod.EventBusSubscriber(modid = "fortune-gives-experience", bus = Mod.EventBusSubscriber.Bus.FORGE)
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



    @SubscribeEvent
    public static void onUpdate(TickEvent.ServerTickEvent event) {
        if(server == null) return;

        List<ServerPlayerEntity> players = server.getPlayerList().getPlayers();

        for(ServerPlayerEntity p : players) {
            logger.debug(p.getScoreboardName() + "  "  + p.getFoodStats().getFoodLevel());
            //p.getFoodStats().addStats();
        }
    }



    private static MinecraftServer server = null;



    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        server = event.getServer();
    }



    public static Logger logger = LogManager.getLogger("fortune-gives-experience");

}