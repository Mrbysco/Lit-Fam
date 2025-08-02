package com.mrbysco.litfam.handler;

import com.mrbysco.litfam.config.LitConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.function.Predicate;

public class GlowHandler {
	private static final Predicate<Entity> RANGE_PREDICATE = (entity) -> {
		if (entity instanceof LivingEntity target) {
			double visibility = target.getVisibilityPercent(entity);
			double d1 = Math.max(LitConfig.COMMON.glowRange.get() * visibility, 2.0);
			double d2 = entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
			if (d2 > d1 * d1) {
				return false;
			}
		}
		return true;
	};

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent.Pre event) {
		Player player = event.getEntity();
		Level level = player.level();
		if (!player.isSpectator() && level.getGameTime() % 5 == 0 && LitConfig.COMMON.glowEnabled.get()) {
			final int range = LitConfig.COMMON.glowRange.get();
			final AABB closeBox = player.getBoundingBox().inflate(range, 5.0D, range);

			List<LivingEntity> closeEntities = level.getEntitiesOfClass(LivingEntity.class, closeBox, (entity) -> RANGE_PREDICATE.test(entity) &&
					entity.canBeSeenByAnyone() && entity != player);
			for (LivingEntity livingEntity : closeEntities) {
				livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 10, 0, true, false));
			}
		}
	}
}