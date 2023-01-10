package net.chauhanDevs.advance_modder;

import com.mojang.logging.LogUtils;
import net.chauhanDevs.advance_modder.test.Anims;
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

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        Anims.init();
        //Outputs That Mod Has Loaded
        LOGGER.debug("Player Animator Loaded Up!");
    }
}
