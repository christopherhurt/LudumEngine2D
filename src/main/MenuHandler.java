package main;

import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuHandler {
    
    private static List<Menu> menus = new CopyOnWriteArrayList<>();
    private static String currentMenu = null;
    
    private MenuHandler(){}
    
    protected static void update(){
        boolean updated = false;
        
        for(Menu menu : menus){
            if(menu.getName().equals(currentMenu)){
                menu.checkButtons();
                updated = true;
            }
        }
        
        if(!updated && currentMenu != null){
            throw new IllegalStateException("Invalid current menu");
        }
    }
    
    protected static void updateFonts(){
        for(Menu menu : menus){
            menu.updateFonts();
        }
    }
    
    protected static void render(Graphics2D g){
        boolean rendered = false;
        
        for(Menu menu : menus){
            if(menu.getName().equals(currentMenu)){
                menu.render(g);
                rendered = true;
            }
        }
        
        if(!rendered && currentMenu != null){
            throw new IllegalStateException("Invalid current menu");
        }
    }
    
    protected static void addMenu(Menu menu){
        for(Menu men : menus){
            if(men.getName().equals(menu.getName())){
                throw new IllegalArgumentException("Tried to add menus with the same names");
            }
        }
        
        if(menus.size() == 0){
            currentMenu = menu.getName();
        }
        
        menus.add(menu);
    }
    
    protected static Menu getMenu(String name){
        for(Menu menu : menus){
            if(menu.getName().equals(name)){
                return menu;
            }
        }
        
        throw new IllegalArgumentException("Tried to get non-existent menu");
    }
    
    protected static String getCurrentMenu(){
        return currentMenu;
    }
    
    protected static void setCurrentMenu(String name){
        currentMenu = name;
    }
    
}
