package com.mrbysco.litfam.util;

import com.mrbysco.litfam.config.LitConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class BrightUtil {
	public static boolean shouldBeBright(Entity entity) {
		final Minecraft minecraft = Minecraft.getInstance();
		return minecraft.player == entity || minecraft.player.distanceTo(entity) <= LitConfig.COMMON.fullBrightRange.get();
	}
}
