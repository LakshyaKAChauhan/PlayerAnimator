package net.chauhanDevs.advance_modder.utils;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;


public final class PlayerModelMixinHelper {
    public static <T extends LivingEntity> void resolvePlayerModel(FixedPlayerModel fixedPlayerModel, PlayerModel<T> model, boolean shouldCopyLayerToItsLayerBelow) {
        Iterator<?> iterator1 = getParts(fixedPlayerModel.getFixedModel()).iterator();
        Iterator<?> iterator2 = getParts(model).iterator();
        while (iterator1.hasNext() && iterator2.hasNext()){
            if(iterator1.next() instanceof ModelPart part1 &&
                    iterator2.next() instanceof ModelPart part2){
                if(!isRotationSameAs(part1, part2)){
                    fromDegreesToMinecraftRotation(part2);
                }
            }
        }
        if (shouldCopyLayerToItsLayerBelow) {
            model.leftSleeve.copyFrom(model.leftArm);
            model.rightSleeve.copyFrom(model.rightArm);
            model.rightPants.copyFrom(model.rightLeg);
            model.leftPants.copyFrom(model.leftLeg);
        }
    }

    public static Iterable<?> getParts(PlayerModel model){
        for (Method method : PlayerModel.class.getDeclaredMethods()) {
            if (method.getName() == "bodyParts") {
                method.setAccessible(true);
                Object returnObj = null;
                try {
                    returnObj = method.invoke(model);
                } catch (Exception e) {
                    System.out.println("Error While Invoking Method!");
                }
                if (returnObj instanceof Iterable<?> iterable) {
                    return iterable;
                }
            }
        }
        return new ArrayList<>();
    }

    public static void fromDegreesToMinecraftRotation(float degreeX, float degreeY, float degreeZ, ModelPart part) {
        float realDegreeX = degreeX / 20;
        float realDegreeY = degreeY / 20;
        float realDegreeZ = degreeZ / 20;
        part.setRotation(realDegreeX, realDegreeY, realDegreeZ);
    }

    public static void fromDegreesToMinecraftRotation(ModelPart part) {
        fromDegreesToMinecraftRotation(part.xRot, part.yRot, part.zRot, part);
    }

    public static boolean isRotationSameAs(ModelPart part1, ModelPart part2){
        if(part1.xRot == part2.xRot && (part1.yRot == part2.yRot && part1.zRot == part2.zRot)){
            return true;
        }else{
            return false;
        }
    }

    public static final class FixedPlayerModel {
        private final PlayerModel fixedModel;

        private FixedPlayerModel(PlayerModel model) {
            fixedModel = model;
        }

        public static FixedPlayerModel of(PlayerModel model) {
            try {
                for (Method method : model.getClass().getDeclaredMethods()) {
                    if (method.getName() == "clone") {
                        if (method.invoke(model) instanceof PlayerModel<?> iteratedModel) {
                            return new FixedPlayerModel(iteratedModel);
                        }
                    }
                }
                return null;
            }catch (Exception e){
                return null;
            }

        }

        public PlayerModel getFixedModel(){
            return fixedModel;
        }

    }
}