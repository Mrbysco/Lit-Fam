package com.mrbysco.litfam.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.litfam.config.LitConfig;
import com.mrbysco.litfam.util.BrightUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererManagerMixin<T extends Entity> {
	@ModifyArg(method = "render(Lnet/minecraft/world/entity/Entity;DDDFFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/entity/EntityRenderer;render(Lnet/minecraft/world/entity/Entity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"),
			index = 5)
	private int litfam$changeBrightness(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int pavketLight) {
		if (entity instanceof LivingEntity && (LitConfig.COMMON.alwaysFullBright.get() || BrightUtil.shouldBeBright(entity))) {
			return 15728880;
		}
		return pavketLight;
	}
}
