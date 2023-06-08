package com.mrbysco.litfam.handler;

import com.mrbysco.litfam.config.LitConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class GlowHandler {
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		Level level = player.level();
		if (player != null && !player.isSpectator() && level.getGameTime() % 5 == 0 && LitConfig.COMMON.glowEnabled.get()) {
			final int range = LitConfig.COMMON.glowRange.get();
			final TargetingConditions closePredicate = (TargetingConditions.forNonCombat()).range(range);
			final AABB closeBox = player.getBoundingBox().inflate(range, 5.0D, range);

			List<LivingEntity> closeEntities = level.getNearbyEntities(LivingEntity.class, closePredicate, player, closeBox);
			for (LivingEntity livingEntity : closeEntities) {
				livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 10, 0, true, false));
			}
		}
	}
}