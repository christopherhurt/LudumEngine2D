package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Scene {
    
    private String id;
    private List<GameObject> objects;
    private Menu menu;
    
    protected Scene(String id) {
        this.id = id;
        objects = new CopyOnWriteArrayList<>();
        menu = null;
    }
    
    protected void update() {
        if(menu != null) {
            menu.checkButtons();
        }
        
        for(GameObject object : objects) {
          if(!Game.isPaused() || object.getUpdateWhilePaused()) {
              if(object instanceof AnimatedGameObject) {
                  Animation animation = ((AnimatedGameObject)object).getAnimation();
                  animation.update();
              }
              object.updatePhysicsComponents();
              object.update();
          }
        }
    }
    
    protected void readdObjects(String objID) {
      List<GameObject> removedObjects = new ArrayList<>();
      
      for(int i = 0; i < objects.size(); i++){
          GameObject currentObject = objects.get(i);
          
          if(currentObject.getID().equals(objID)){
              removedObjects.add(objects.remove(i));
              i--;
          }
      }
      
      for(GameObject object : removedObjects){
          objects.add(object);
      }
    }
    
    public List<GameObject> getObjectList() {
        return objects;
    }
    
    protected int getObjectCount() {
        return objects.size();
    }
    
    protected void updateMenuFonts() {
        if(menu != null) {
            menu.updateFonts();
        }
    }
    
    protected void render(Graphics2D g) {
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
        
        if(menu != null) {
            menu.render(g);
        }
    }
    
    protected void attachMenu(Menu menu) {
        this.menu = menu;
    }
    
    protected void detachMenu() {
        menu = null;
    }
    
    protected boolean addObject(GameObject obj) {
        return objects.add(obj);
    }
    
    protected boolean removeObject(GameObject obj) {
        return objects.remove(obj);
    }
    
    protected void clearObjects() {
        objects.clear();
    }
    
    protected List<GameObject> getObjects(String id) {
        List<GameObject> matches = new CopyOnWriteArrayList<>();
        
        for(GameObject obj : objects) {
            if(obj.getID().equals(id)) {
                matches.add(obj);
            }
        }
        
        return matches;
    }
    
    protected Menu getMenu() {
        return menu;
    }
    
    protected String getID() {
        return id;
    }
    
}
