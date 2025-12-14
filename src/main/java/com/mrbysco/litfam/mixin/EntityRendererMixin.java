package com.mrbysco.litfam.mixin;

import com.mrbysco.litfam.config.LitConfig;
import com.mrbysco.litfam.util.BrightUtil;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<E extends Entity> {
	@Inject(method = "getBlockLightLevel(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;)I",
			at = @At(
					value = "HEAD"
			), cancellable = true)
	public void litfam$getBlockLightLevel(E entity, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
		if (entity instanceof LivingEntity && (LitConfig.COMMON.alwaysFullBright.get() || BrightUtil.shouldBeBright(entity))) {
			cir.setReturnValue(15);
		}
	}
}
