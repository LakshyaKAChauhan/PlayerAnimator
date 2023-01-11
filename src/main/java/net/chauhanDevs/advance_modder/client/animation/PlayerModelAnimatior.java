package net.chauhanDevs.advance_modder.client.animation;

import net.minecraft.client.model.geom.ModelPart;

public class PlayerModelAnimatior {
    public static void rotateModelPart(ModelPart part, float x, float y, float z){
        /**
         * Not Accurate!
         */
        part.setRotation((float) (x / (90 / 1.575)), (float) (y / (90 / 1.575)), (float) (z / (90 / 1.575)));
    }
}
