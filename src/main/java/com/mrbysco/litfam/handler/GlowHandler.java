package com.mrbysco.litfam.handler;

import com.mrbysco.litfam.config.LitConfig;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class GlowHandler {
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		PlayerEntity player = event.player;
		if(player != null && !player.isSpectator() && player.level.getGameTime() % 5 == 0) {
			final int range = LitConfig.COMMON.glowRange.get();
			final EntityPredicate closePredicate = (new EntityPredicate()).range(range);
			final AxisAlignedBB closeBox = player.getBoundingBox().inflate(range, 5.0D, range);

			List<LivingEntity> closeEntities = player.level.getNearbyEntities(LivingEntity.class, closePredicate, player, closeBox);
			for(LivingEntity livingEntity : closeEntities) {
				livingEntity.addEffect(new EffectInstance(Effects.GLOWING,10, 0, true, false));
			}
		}
	}
}