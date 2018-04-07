package main;

public final class Animation {
    
    private float length;
    private Texture[] textures;
    
    private float timer;
    
    public Animation(float length, Texture ... textures){
        this.length = length;
        this.textures = textures;
        timer = 0;
    }
    
    protected void update(){
        timer++;
    }
    
    protected Texture getCurrentTexture(){
        float numSeconds = (timer * Game.getOneOverFps()) % length;
        int index = (int)(numSeconds / length * textures.length);
        return textures[index];
    }
    
    public float getLength(){
        return length;
    }
    
    public void setLength(float length){
        this.length = length;
    }
    
    public void restart(){
        timer = 0;
    }
    
}
