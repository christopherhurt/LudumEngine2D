package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Manager {
    
    private List<GameObject> objects;
    private String name;
    
    public Manager(String name){
        this.name = name;
        objects = new CopyOnWriteArrayList<>();
        
        ManagerHandler.addManager(this);
    }
    
    protected void update(){
        for(GameObject object : objects){
            if(!Game.isPaused() || object.getUpdateWhilePaused()){
                if(object instanceof AnimatedGameObject){
                    Animation animation = ((AnimatedGameObject)object).getAnimation();
                    animation.update();
                }
                object.updatePhysicsComponents();
                object.update();
            }
        }
    }
    
    protected void render(Graphics2D g){
        for(GameObject object : objects){
            if(object.getCameraAffected()){
                int xTrans = (int)(-Camera.getX() * Window.getWidth());
                int yTrans = (int)(-Camera.getY() * Window.getHeight());
                g.translate(xTrans, yTrans);
                object.render(g);
                g.translate(-xTrans, -yTrans);
            }else{
                object.render(g);
            }
        }
    }
    
    public void addObject(GameObject object){
        objects.add(object);
    }
    
    public void readdObjects(String id){
        List<GameObject> removedObjects = new ArrayList<>();
        
        for(int i = 0; i < objects.size(); i++){
            GameObject currentObject = objects.get(i);
            
            if(currentObject.getID().equals(id)){
                removedObjects.add(objects.remove(i));
                i--;
            }
        }
        
        for(GameObject object : removedObjects){
            objects.add(object);
        }
    }
    
    public void removeObject(GameObject object){
        objects.remove(object);
    }
    
    public void clearObjects(){
        objects.clear();
    }
    
    public List<GameObject> getObjects(String id){
        List<GameObject> list = new ArrayList<>();
        
        for(GameObject object : objects){
            if(object.getID().equals(id)){
                list.add(object);
            }
        }
        
        return list;
    }
    
    public GameObject getObject(int index){
        return objects.get(index);
    }
    
    public int getObjectCount(){
        return objects.size();
    }
    
    protected String getName(){
        return name;
    }
    
}
