package net.chauhanDevs.advance_modder.mixins.helper;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.lang.reflect.Method;

public final class PlayerModelMixinHelper {
    public static <T extends LivingEntity> void resolvePlayerModel(PlayerModel<T> model, boolean shouldCopyLayerToItsLayerBelow) {
        for (Method method : PlayerModel.class.getDeclaredMethods()) {
            if(method.getName() == "bodyParts"){
                method.setAccessible(true);
                Object returnObj = null;
                try {
                    returnObj = method.invoke(model);
                } catch (Exception e) {
                    System.out.println("Error While Invoking Method!");
                }
                if(returnObj instanceof Iterable<?> iterable){
                    for (Object obj : iterable) {
                        if(obj instanceof ModelPart part){
                            fromDegreesToMinecraftRotation(part);
                        }
                    }
                }
            }
        }
        if(shouldCopyLayerToItsLayerBelow) {
            model.leftSleeve.copyFrom(model.leftArm);
            model.rightSleeve.copyFrom(model.rightArm);
            model.rightPants.copyFrom(model.rightLeg);
            model.leftPants.copyFrom(model.leftLeg);
        }
    }
    
    public static void fromDegreesToMinecraftRotation(float degreeX, float degreeY, float degreeZ, ModelPart part){
        float realDegreeX = degreeX / 20;
        float realDegreeY = degreeY / 20;
        float realDegreeZ = degreeZ / 20;
        part.setRotation(realDegreeX, realDegreeY, realDegreeZ);
    }

    public static void fromDegreesToMinecraftRotation(ModelPart part){
        fromDegreesToMinecraftRotation(part.xRot, part.yRot, part.zRot, part);
    }
}
