package net.chauhanDevs.advance_modder.util;

import net.chauhanDevs.advance_modder.client.animation.Animation;
import net.chauhanDevs.advance_modder.exceptions.AnimationWithSameIdExists;
import net.chauhanDevs.advance_modder.registeredAnimations.registeredAnimations;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class AnimationManagingHelper {
    public static void registerAnimation(Animation animation){
        if(Objects.isNull(getAnimation(animation.getId()))){
            registeredAnimations.animations.add(animation);
        }else{
            throw new AnimationWithSameIdExists();
        }
    }

    @Nullable
    public static Animation getAnimation(String AnimationId){
        for (Animation animation : registeredAnimations.animations) {
            if(Objects.equals(animation.getId(), AnimationId)){
                return animation;
            }
        }
        return null;
    }
}
