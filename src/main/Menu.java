package main;

import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Menu {
    
    private String name;
    private List<MenuButton> buttons;
    private List<MenuLabel> labels;
    
    public Menu(String name){
        this.name = name;
        buttons = new CopyOnWriteArrayList<>();
        labels = new CopyOnWriteArrayList<>();
    }
    
    protected void updateFonts(){
        for(MenuButton button : buttons){
            button.getLabel().updateFont();
        }
        for(MenuLabel label : labels){
            label.updateFont();
        }
    }
    
    protected void checkButtons(){
        for(MenuButton button : buttons){
            button.checkButtons();
            button.update();
        }
    }
    
    protected void render(Graphics2D g){
        for(MenuButton button : buttons){
            button.render(g);
        }
        for(MenuLabel label : labels){
            label.render(g);
        }
    }
    
    public void addLabel(MenuLabel label){
        for(MenuLabel lab : labels){
            if(lab.getID().equals(label.getID())){
                throw new IllegalArgumentException("Tried to add two menu labels with same ID to same menu");
            }
        }
        
        labels.add(label);
    }
    
    public void addButton(MenuButton button){
        for(MenuButton butt : buttons){
            if(butt.getID().equals(button.getID())){
                throw new IllegalArgumentException("Tried to add two menu buttons with same ID to same menu");
            }
        }
        
        buttons.add(button);
    }
    
    public MenuLabel getLabel(String id){
        for(MenuLabel label : labels){
            if(label.getID().equals(id)){
                return label;
            }
        }
        
        throw new IllegalArgumentException("Entered non-existent ID when getting menu label");
    }
    
    public MenuButton getButton(String id){
        for(MenuButton button : buttons){
            if(button.getID().equals(id)){
                return button;
            }
        }
        
        throw new IllegalArgumentException("Entered non-existent ID when getting menu button");
    }
    
    public void clearAll(){
        labels.clear();
        buttons.clear();
    }
    
    protected String getName(){
        return name;
    }
    
}
