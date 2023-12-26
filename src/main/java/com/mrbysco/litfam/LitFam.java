package com.mrbysco.litfam;

import com.mojang.logging.LogUtils;
import com.mrbysco.litfam.config.LitConfig;
import com.mrbysco.litfam.handler.GlowHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(LitFam.MOD_ID)
public class LitFam {
	public static final String MOD_ID = "litfam";
	public static final Logger LOGGER = LogUtils.getLogger();

	public LitFam(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LitConfig.commonSpec);
		eventBus.register(LitConfig.class);

		NeoForge.EVENT_BUS.register(new GlowHandler());
	}
}
