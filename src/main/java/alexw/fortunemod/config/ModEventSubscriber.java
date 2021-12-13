package alexw.fortunemod.config;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = "fortune_gives_experience", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {


    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent configEvent) {
      //  if (configEvent.getConfig().getSpec() == Config.SPEC_PAIR.getRight()) Config.bakeConfig();
    }


}
