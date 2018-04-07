package main;

import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ManagerHandler {
    
    private static List<Manager> managers = new CopyOnWriteArrayList<>();
    private static String currentManager = null;
    
    private ManagerHandler(){}
    
    protected static void update(){
        boolean updated = false;
        
        for(Manager manager : managers){
            if(manager.getName().equals(currentManager)){
                manager.update();
                updated = true;
            }
        
        }
        if(!updated && currentManager != null){
            throw new IllegalStateException("Invalid current manager");
        }
    }
    
    protected static void render(Graphics2D g){
        boolean rendered = false;
        
        for(Manager manager : managers){
            if(manager.getName().equals(currentManager)){
                manager.render(g);
                rendered = true;
            }
        }
        
        if(!rendered && currentManager != null){
            throw new IllegalStateException("Invalid current manager");
        }
    }
    
    protected static void addManager(Manager manager){
        for(Manager man : managers){
            if(man.getName().equals(manager.getName())){
                throw new IllegalArgumentException("Tried to add managers with the same names");
            }
        }
        
        if(managers.size() == 0){
            currentManager = manager.getName();
        }
        
        managers.add(manager);
    }
    
    protected static Manager getManager(String name){
        for(Manager manager : managers){
            if(manager.getName().equals(name)){
                return manager;
            }
        }
        
        throw new IllegalArgumentException("Tried to get non-existent manager");
    }
    
    protected static String getCurrentManager(){
        return currentManager;
    }
    
    protected static void setCurrentManager(String name){
        currentManager = name;
    }
    
}
