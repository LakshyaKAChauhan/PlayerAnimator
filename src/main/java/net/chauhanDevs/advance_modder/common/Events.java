package net.chauhanDevs.advance_modder.common;

import net.chauhanDevs.advance_modder.Reference;
import net.minecraftforge.fml.common.Mod;


public class Events {

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public class ForgeEvents{
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ModEvents{
    }
}
