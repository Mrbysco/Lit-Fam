package com.mrbysco.litfam;

import com.mojang.logging.LogUtils;
import com.mrbysco.litfam.config.LitConfig;
import com.mrbysco.litfam.handler.GlowHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LitFam.MOD_ID)
public class LitFam {
	public static final String MOD_ID = "litfam";
	public static final Logger LOGGER = LogUtils.getLogger();

	public LitFam() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LitConfig.commonSpec);
		eventBus.register(LitConfig.class);

		MinecraftForge.EVENT_BUS.register(new GlowHandler());
	}
}
