package alexw.fortunemod;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("fortune_gives_experience")
public class FortuneGivesExperience {


	public FortuneGivesExperience() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC_PAIR.getRight());
	}


}
