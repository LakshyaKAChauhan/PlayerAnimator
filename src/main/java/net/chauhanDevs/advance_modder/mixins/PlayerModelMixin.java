package net.chauhanDevs.advance_modder.mixins;

import net.chauhanDevs.advance_modder.client.animation.Animation;
import net.chauhanDevs.advance_modder.utils.PlayerModelMixinHelper;
import net.chauhanDevs.advance_modder.registeredAnimations.registeredAnimations;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerModel.class})
public abstract class PlayerModelMixin<T extends LivingEntity> {

    @Inject(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = @At("TAIL"))
    protected void onSetupAnim(T entity, float animPos, float animSpeed, float animBob, float headYaw, float headPitch, CallbackInfo ci){
        if(entity instanceof Player plr){
            int int1 = (int) animPos;
            float float1 = animPos - int1;
            float float2 = float1 * 10;
            int animationPosition = (int) float2;
            boolean shouldCopyLayerToItsLayerBelow = false;
            PlayerModel model = (PlayerModel) (Object) this;
            PlayerModelMixinHelper.FixedPlayerModel fixedPlayerModel = PlayerModelMixinHelper.FixedPlayerModel.of(model);
            for (Animation animation : registeredAnimations.animations){
                if(!animation.isPlaying()){
                    shouldCopyLayerToItsLayerBelow = animation.onPlay(plr, model, animationPosition);
                    if(animation.getProperties().shouldResetAtEnd()){
                      animation.stop();
                    }
                }
            }
            //Fix Model, From Degrees To Minecraft Rotation
            assert fixedPlayerModel != null;
            PlayerModelMixinHelper.resolvePlayerModel(fixedPlayerModel, model, shouldCopyLayerToItsLayerBelow);
        }
    }


}

