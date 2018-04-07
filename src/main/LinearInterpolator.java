package main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LinearInterpolator {
    
    private static List<LerpedFloat> values = new CopyOnWriteArrayList<>();
    
    private LinearInterpolator(){}
    
    protected static void addValue(LerpedFloat value){
        values.add(value);
    }
    
    protected static void update(){
        for(int i = 0; i < values.size(); i++){
            LerpedFloat current = values.get(i);
            current.update();
        }
    }
    
}
