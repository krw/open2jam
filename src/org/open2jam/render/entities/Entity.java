package org.open2jam.render.entities;

import org.open2jam.util.Copyable;
import org.open2jam.render.Sprite;
import org.open2jam.render.SpriteList;

import aurelienribon.tweenengine.Tweenable;

public class Entity  implements Copyable<Entity>, Tweenable
{
    /** tweens types */
    public static final int X = 1;
    public static final int Y = 2;
    public static final int XY = 3;
    
    SpriteList frames;
    Sprite sprite;

    /** The current speed of this entity horizontally (pixels/millisecs) */
    private double dx;
    /** The current speed of this entity vertically (pixels/millisecs) */
    private double dy;

    /** this object stores the position(x,y) and dimensions (width,height) */
    double x;
    double y;
    double width;
    double height;


    /** when a entity die the render removes it */
    boolean dead = false;

    private int layer = 0;

    /** allows constructor extensions */
    Entity() {}

    public Entity(SpriteList sp, double x, double y)
    {
        this.frames = sp;
        this.sprite = sp.get(0);
        this.x = x;
        this.y = y;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public Entity(Sprite s, double x, double y)
    {
        this.frames = null;
        this.sprite = s;
        this.x = x;
        this.y = y;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    protected Entity(Entity org) {
        this.dead = org.dead;
        this.sprite = org.sprite;
        this.frames = org.frames;
        this.dx = org.dx;
        this.dy = org.dy;
        this.x = org.x;
        this.y = org.y;
        this.width = org.width;
        this.height = org.height;
        this.layer = org.layer;
    }

    public boolean isDead() { return dead; }

    public void setDead(boolean state){
        dead = state;
    }

    /**
     * Request that this entity move itself based on a certain ammount
     * of time passing.
     *
     * @param delta The ammount of time that has passed in milliseconds
     */
    public void move(double delta) {
        // update the location of the entity based on move speeds
        x += delta * dx;
        y += delta * dy;
    }

    /**
     * Sets the moving speed of this entity
     */
    public void setXMove(double dx) { this.dx = dx; }
    public void setYMove(double dy) { this.dy = dy; }

    /**
     * Gets the moving speed of this entity
     */
    public double getXMove() { return dx; }
    public double getYMove() { return dy; }

    public void setAlpha(float alpha)
    {
	sprite.setAlpha(alpha);
    }

    /**
     * Draw this entity to the graphics context provided
     */
    public void draw() {
        sprite.draw(x,y, sprite.getScaleX(), sprite.getScaleY());
    }

    public double getX(){ return x;}
    public double getY(){ return y;}

    public void setPos(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getWidth(){ return width;}
    public double getHeight(){ return height;}

    public void setLayer(int layer){ this.layer = layer; }
    public int getLayer(){ return layer; }

    public SpriteList getFrames(){
        return frames;
    }

    @Override
    public Entity copy() {
        return new Entity(this);
    }

    @Override
    public int getTweenValues(int tween, float[] returnValues) {
        switch(tween)
        {
            case X: 
                returnValues[0] = (float) x;
                return 1;
            case Y: 
                returnValues[0] = (float) y;
                return 1;
            case XY:
                returnValues[0] = (float) x;
                returnValues[1] = (float) y;
                return 2;
            
                
            default: assert false; return 0;
        }
    }

    @Override
    public void onTweenUpdated(int tween, float[] newValues) {
        switch(tween)
        {
            case X: 
                this.setPos(newValues[0], y);
            break;
            case Y: 
                this.setPos(x, newValues[0]);
            break;
            case XY:
                this.setPos(newValues[0], newValues[1]);
            break;
            
                
            default: assert false; break;
        }
    }
}
