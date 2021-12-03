package com.mrbysco.litfam.util;

import com.mrbysco.litfam.config.LitConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class BrightUtil {
	public static boolean shouldBeBright(Entity entity) {
		final Minecraft minecraft = Minecraft.getInstance();
		return minecraft.player != null && (minecraft.player == entity || minecraft.player.distanceTo(entity) <= LitConfig.COMMON.fullBrightRange.get());
	}
}
