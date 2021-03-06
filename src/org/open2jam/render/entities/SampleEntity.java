/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.open2jam.render.entities;

import org.open2jam.parser.Event;
import org.open2jam.render.Render;

/**
 *
 * @author fox
 */
public class SampleEntity extends Entity implements TimeEntity
{
    private final Event.SoundSample value;
    private final Render render;

    private double time_to_hit;

    public SampleEntity(Render r, Event.SoundSample value, double y)
    {
        this.render = r;
        this.value = value;
        this.x = 0;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }

    private SampleEntity(SampleEntity org) {
        super(org);
        this.value = org.value;
        this.render = org.render;
    }

    @Override
    public void move(double delta) {}

    @Override
    public void judgment()
    {
         render.queueSample(value);
         dead = true;
    }

    
    @Override
    public void draw() {}

    @Override
    public SampleEntity copy(){
        return new SampleEntity(this);
    }

    @Override
    public void setTime(double t) {
        this.time_to_hit = t;
    }

    @Override
    public double getTime() {
        return time_to_hit;
    }
}
