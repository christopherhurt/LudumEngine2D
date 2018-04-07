package main;

public final class LerpedFloat {
    
    private float initialValue;
    private float currentValue;
    private float finalValue;
    private float duration;
    private float timestep;
    private boolean finished;
    
    public LerpedFloat(float initialValue, float finalValue, float duration){
        this.initialValue = initialValue;
        this.finalValue = finalValue;
        this.duration = duration;
        currentValue = initialValue;
        
        timestep = 0;
        finished = true;
        
        LinearInterpolator.addValue(this);
    }
    
    public void start(){
        if(!Game.isInitialized()){
            throw new IllegalStateException("Cannot start LerpedFloat until Game has been initialized");
        }
        timestep = (finalValue - initialValue) * Game.getOneOverFps() / duration;
        currentValue = initialValue;
        finished = false;
    }
    
    protected void update(){
        if(!finished){
            currentValue += timestep;
            
            if(currentValue >= finalValue){
                currentValue = finalValue;
                finished = true;
            }
        }
    }
    
    public boolean isFinished(){
        return finished;
    }
    
    public float getCurrentValue(){
        return currentValue;
    }
    
}
