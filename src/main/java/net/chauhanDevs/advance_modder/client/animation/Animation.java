package net.chauhanDevs.advance_modder.client.animation;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public abstract class Animation{
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
     * <h2>Specify Your Animation By Overriding This Method Here!</h2>
     * <br>
     * Whole Example Mentioned Below:<br>
     * <br>
     * E.G:<br>
     * if(frame == 0.5){<br>
     * &#9model.bodypart.setRotation(0, 45, 45)<br>
     * }else if(frame == 1){<br>
     * &#9model.bodypart2.setRotation(0, 90, 90)<br>
     * }<br>
     * //Additional Code<br><br>
     * <h3>@return Tells If The Above Skin Layer Should Be Copied From Below Layer</h3>
     */
    public boolean onPlay(Player player, PlayerModel model, int frame){return true;}

    public void play(){
        this.isPlaying = true;
    }

    public final void stop(){
        this.isPlaying = false;
    }

    public boolean isPlaying(){return this.isPlaying;}

    public @NotNull String getId(){return this.Id;}

    public Animation.@NotNull Properties getProperties(){return this.properties;}

    public static class Properties{
        /**
         * WARNING: EXPERIMENTAL PROPERTY!
         */
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
