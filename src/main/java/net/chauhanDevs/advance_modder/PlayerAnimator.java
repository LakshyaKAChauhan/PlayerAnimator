package net.chauhanDevs.advance_modder;

import com.mojang.logging.LogUtils;
import com.mojang.math.Vector3d;
import net.chauhanDevs.advance_modder.client.animation.Animation;
import net.chauhanDevs.advance_modder.client.animation.PlayerModelAnimatior;
import net.chauhanDevs.advance_modder.test.Items;
import net.chauhanDevs.advance_modder.utils.AnimationManagingHelper;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID)
public class PlayerAnimator
{
    public static final Logger LOGGER = LogUtils.getLogger();

    public PlayerAnimator()
    {
        //Outputs That Mod Is Loading
        LOGGER.debug("Loading Player Animator Mod");
        //LOGO MAKING
        /*AnimationManagingHelper.registerAnimation(new Animation(new Animation.Properties(), "logo") {
            @Override
            public boolean onPlay(Player player, PlayerModel model, int frame) {
                PlayerModelAnimatior.rotateModelPart(model.leftArm, -15, 15, -125);
                PlayerModelAnimatior.rotateModelPart(model.rightArm, -15, -15, 125);
                PlayerModelAnimatior.rotateModelPart(model.head, -40,0,0);
                return true;
            }
        });
        AnimationManagingHelper.getAnimation("logo").play();*/
        //LOGO MAKING END
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        //Items.def.register(bus);
        //Outputs That Mod Has Loaded
        LOGGER.debug("Player Animator Loaded Up!");
    }
}
