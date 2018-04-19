package main;

import java.awt.Graphics2D;
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
        
        for(GameObject obj : objects) {
            obj.update();
        }
    }
    
    protected void updateMenuFonts() {
        if(menu != null) {
            menu.updateFonts();
        }
    }
    
    protected void render(Graphics2D g) {
        for(GameObject obj : objects) {
            obj.render(g);
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
    
    protected void addObject(GameObject obj) {
        objects.add(obj);
    }
    
    protected void removeObject(GameObject obj) {
        objects.remove(obj);
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
