package net.chauhanDevs.advance_modder.mixins;

import net.chauhanDevs.advance_modder.client.animation.Animation;
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
            boolean shouldCopyLayerToItsLayerBelow = true;
            PlayerModel model = (PlayerModel) (Object) this;for (Animation animation : registeredAnimations.animations){
                if(!animation.isPlaying()){
                    boolean playResult = animation.onPlay(plr, model, animationPosition);
                    if(!playResult){
                        shouldCopyLayerToItsLayerBelow = false;
                    }
                    /*if(animation.getProperties().shouldResetAtEnd()){
                      animation.stop();
                    }*/
                }
            }
            if(shouldCopyLayerToItsLayerBelow){
                model.leftSleeve.copyFrom(model.leftArm);
                model.rightSleeve.copyFrom(model.rightArm);
                model.leftPants.copyFrom(model.leftLeg);
                model.rightPants.copyFrom(model.rightLeg);
            }
        }
    }


}

