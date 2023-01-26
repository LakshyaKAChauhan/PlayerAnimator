package net.chauhanDevs.advance_modder.utils;

import net.chauhanDevs.advance_modder.client.animation.AnimateAbleItem;
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

    /**
     * WARNING: EXPERIMENTAL FEATURE!
     * */
    public static void registerItemAnimation(AnimateAbleItem animateAbleItem){
        if(Objects.isNull(getItemAnimation(animateAbleItem.getId()))){
            registeredAnimations.itemAnimatons.add(animateAbleItem);
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


    /**
     * WARNING: EXPERIMENTAL FEATURE!
     * */
    @Nullable
    public static AnimateAbleItem getItemAnimation(String AnimationId){
        for (AnimateAbleItem item : registeredAnimations.itemAnimatons) {
            if(Objects.equals(item.getId(), AnimationId)){
                return item;
            }
        }
        return null;
    }
}
