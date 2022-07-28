package alexw.fortunemod;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "fortune_gives_experience", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {


    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        if(event.getPlayer() == null) return;

        int fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, event.getPlayer().getMainHandItem());

        if(fortune > 0) event.setExpToDrop((int) Math.ceil(event.getExpToDrop() * (1 + fortune * Config.fortuneMultiplier())));
    }


}