package net.chauhanDevs.advance_modder.exceptions;

public class AnimationWithSameIdExists extends RuntimeException{
    public AnimationWithSameIdExists(){
        super("An Animation With The Same Id Already Exists, Please Use Another Id To Create Animation!");
    }
}
