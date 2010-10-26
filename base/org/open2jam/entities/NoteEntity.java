package org.open2jam.entities;

import org.open2jam.render.SpriteList;
import org.open2jam.render.Render;

/** a NoteEntity is a animated entity which moves down.
*** it moves according to a Chart */
public class NoteEntity extends AnimatedEntity
{
	public NoteEntity(SpriteList sl, double x, double y)
	{
		super(sl, x, y);
	}

	protected Render render;

	public void setRender(Render r)
	{
		this.render = r;
	}

	public void move(long delta)
	{
		setYMove(render.getNoteSpeed());
		super.move(delta);
	}

	public void judgment()
	{
		alive = false;
	}
}