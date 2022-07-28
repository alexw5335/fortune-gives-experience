package alexw.fortunemod;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {


	public static final Pair<Config, ForgeConfigSpec> SPEC_PAIR = new ForgeConfigSpec.Builder().configure(Config::new);



	public static double lootingMultiplier() {
		return SPEC_PAIR.getLeft().lootingMultiplier.get();
	}



	public static double fortuneMultiplier() {
		return SPEC_PAIR.getLeft().fortuneMultiplier.get();
	}



	public final ForgeConfigSpec.DoubleValue lootingMultiplier;

	public final ForgeConfigSpec.DoubleValue fortuneMultiplier;



	public Config(ForgeConfigSpec.Builder builder) {
		lootingMultiplier = builder
			.comment("XP gain is multiplied by (1 + lootingLevel * lootingMultiplier). Default is 0.5, giving x2.5 xp at looting 3.")
			.defineInRange("Looting Multiplier", 0.5, 0, 1000);

		fortuneMultiplier = builder
			.comment("XP gain is multiplied by (1 + fortuneLevel * fortuneMultiplier). Default is 0.5, giving x2.5 xp at fortune 3.")
			.defineInRange("Fortune Multiplier", 0.5, 0, 1000);
	}


}
