package com.mrbysco.litfam.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mrbysco.litfam.config.LitConfig;
import com.mrbysco.litfam.util.BrightUtil;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererManagerMixin<T extends Entity> {
	@ModifyArg(method = "render(Lnet/minecraft/world/entity/Entity;DDDFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;render(Lnet/minecraft/world/entity/Entity;DDDFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/EntityRenderer;)V"),
			index = 7)
	private int litfam$changeBrightness(int packedLight, @Local Entity entity) {
		if (entity instanceof LivingEntity && (LitConfig.COMMON.alwaysFullBright.get() || BrightUtil.shouldBeBright(entity))) {
			return 15728880;
		}
		return packedLight;
	}
}
