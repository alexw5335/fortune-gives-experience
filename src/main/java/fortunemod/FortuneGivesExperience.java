package fortunemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class FortuneGivesExperience implements ModInitializer {


	public static double fortuneMultiplier = 0.5;

	public static double lootingMultiplier = 0.5;



	@Override
	public void onInitialize() { readConfig(); }



	private static void readConfig() {
		File file = new File(FabricLoader.getInstance().getConfigDir().toString() + "/fortuneGivesExperience.txt");

		if(!file.exists()) {
			writeConfig(file);
			return;
		}

		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			boolean invalid = false;

			for(String line : reader.lines().toList()) {
				if(line.startsWith("fortuneMultiplier")) {
					try {
						fortuneMultiplier = Double.parseDouble(line.split("=")[1].trim());
					} catch(NumberFormatException ignored) { invalid = true; }
				} else if(line.startsWith("lootingMultiplier")) {
					try {
						lootingMultiplier = Double.parseDouble(line.split("=")[1].trim());
					} catch(NumberFormatException ignored) { invalid = true; }
				}
			}

			if(invalid)
				writeConfig(file);
		} catch(Exception e) { e.printStackTrace(); }
	}



	private static void writeConfig(File file) {
		try(PrintWriter writer = new PrintWriter(file)) {
			writer.println("# Experience dropped when breaking ore blocks is multiplied by (1 + tool's fortune level * fortuneMultiplier)");
			writer.println("# Default value is 0.5, which gives 1.5x exp for fortune 1, 2.0x for fortune2, and 2.5x for fortune 3");
			writer.println("fortuneMultiplier = 0.5");
			writer.println("");
			writer.println("# Experience dropped when killing mobs is multiplied by (1 + weapon's looting level * lootingMultiplier)");
			writer.println("# Default value is 0.5, which gives 1.5x exp for looting 1, 2.0x for looting 2, and 2.5x for looting 3");
			writer.println("lootingMultiplier = 0.5");
		} catch(Exception e) {e.printStackTrace();}
	}


}
