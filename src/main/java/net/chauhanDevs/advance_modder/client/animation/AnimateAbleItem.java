package net.chauhanDevs.advance_modder.client.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * WARNING: EXPERIMENTAL FEATURE!
 * */
public abstract class AnimateAbleItem extends Item {
    @NotNull private final String Id;

    public AnimateAbleItem(Properties p_41383_, String Id) {
        super(p_41383_);
        this.Id = Id;
    }


    /**
     * Make Sure TO Use Super!
     */
    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        var renderer = (PlayerRenderer) (LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);
        if(player.getMainHandItem() == stack){
            thirdPersonUsing(InteractionHand.MAIN_HAND, (Player) player, renderer.getModel(), count);
        }else if(player.getOffhandItem() == stack) {
            thirdPersonUsing(InteractionHand.OFF_HAND, (Player) player, renderer.getModel(), count);
        }
    }


    /**
     * Make Sure TO Use Super!
     */
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        var renderer = (PlayerRenderer) (LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
        if(entity.getMainHandItem() == stack){
            thirdPersonUsing(InteractionHand.MAIN_HAND, (Player) entity, renderer.getModel(), 0);
        }else if(entity.getOffhandItem() == stack) {
            thirdPersonUsing(InteractionHand.OFF_HAND, (Player) entity, renderer.getModel(), 0);
        }
        return false;
    }

    public int getAnimationDuration(){return 1;}
    public boolean thirdPersonHeld(InteractionHand heldingHand, Player player, PlayerModel model, int frame){return true;}
    public boolean thirdPersonUsing(InteractionHand heldingHand, Player player, PlayerModel model, int frame){return true;}
    public boolean thirdPersonAttacking(InteractionHand heldingHand, Player player, PlayerModel model, int frame){return true;}

    public String getId() {return this.Id;}

    public enum ItemAnimationType {
        THIRD_HELD,
        THIRD_USE,
        THIRD_ATTACK,
        FIRST_HELD,
        FIRST_USE,
        FIRST_ATTACK
    }
}
