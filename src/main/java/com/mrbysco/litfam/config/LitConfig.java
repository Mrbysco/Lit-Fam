package com.mrbysco.litfam.config;

import com.mrbysco.litfam.LitFam;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class LitConfig {

	public static class Common {
		public final BooleanValue alwaysFullBright;
		public final IntValue fullBrightRange;
		public final IntValue glowRange;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			alwaysFullBright = builder
					.comment("Defines if mobs always show full-bright [Default: false]")
					.define("alwaysFullBright", false);

			fullBrightRange = builder
					.comment("Defines the range in which mobs will show full-bright (Unless \"alwaysFullBright\" is set to true) [Default: 32] ")
					.defineInRange("fullBrightRange", 32, 1, Integer.MAX_VALUE);

			glowRange = builder
					.comment("Defines how close a mob has to be to start glowing [Default: 6]")
					.defineInRange("glowRange", 6, 1, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
		LitFam.LOGGER.debug("Loaded It's Lit Fam's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading configEvent) {
		LitFam.LOGGER.debug("It's Lit Fam's config just got changed on the file system!");
	}
}
