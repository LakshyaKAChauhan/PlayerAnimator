package net.chauhanDevs.advance_modder.client.animation;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class Animation{
    @NotNull private Animation.Properties properties;
    @NotNull private final String Id;

    /**
     * @param properties The Properties Of This Animation
     */
    public Animation(Animation.Properties properties, String Id){
        this.properties = properties; this.Id = Id;
    }

    private boolean isPlaying = false;

    /**
     * Specify Your Animation By Overriding This Method Here!<br>
     * <br>
     * Whole Example Mentioned Below:<br>
     * <br>
     * E.G:<br>
     * if(frame == 0.5){<br>
     * &#9model.bodypart.setRotation(0, 45, 45)<br>
     * }else if(frame == 1){<br>
     * &#9model.bodypart2.setRotation(0, 90, 90)<br>
     * }<br>
     * //Additional Code<br>
     */
    public void onPlay(Player player, PlayerModel model, int frame){}

    public final boolean shouldPlay(Player player, int frame){
        //Get Flag1
        boolean flag1;
        if(player.isLocalPlayer()){
            flag1 = frame != 0;
        }else{
            flag1 = false;
        }

        //Get Flag2
        boolean flag2 = isPlaying();

        return flag1 && flag2;
    }

    public void play(){
        this.isPlaying = true;
    }

    public final void stop(){
        this.isPlaying = false;
    }

    public boolean isPlaying(){return this.isPlaying;}

    public String getId(){return this.Id;}

    public Animation.Properties getProperties(){return this.properties;}

    public static class Properties{
        boolean shouldResetAtEnd = true;

        public Animation.Properties shouldResetAtEnd(boolean shouldResetAtEnd){
            this.shouldResetAtEnd = shouldResetAtEnd;
            return this;

        }

        public boolean shouldResetAtEnd(){
            return this.shouldResetAtEnd;
        }
    }
}
