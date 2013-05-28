package com.example.fivecircles;

import java.util.ArrayList;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontLibrary;
import org.andengine.opengl.font.FontUtils;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;



public class BackgroundRectangle extends Rectangle implements IBackgroundRectangle, Observable{
	
	private boolean isChecked = false;
	private ArrayList<IBackgroundRectangle> neighbors;
	private IPlayer player;
	private int id;
	private ArrayList<Observer> observers;
	
	public BackgroundRectangle(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		
		this.neighbors = new ArrayList<IBackgroundRectangle>();
		this.setColor(0.1f,0.1f,0.1f,0.1f);
		this.observers = new ArrayList<Observer>();
	}

	@Override
	public synchronized void addNeighbor(IBackgroundRectangle rectangle) {
		// TODO Auto-generated method stub
		this.neighbors.add(rectangle);
	}

	@Override
	public void removeNeighbor(IBackgroundRectangle rectangle) {
		// TODO Auto-generated method stub
		this.neighbors.remove(rectangle);
	}

	@Override
	public synchronized void checkPath() {
		// TODO Auto-generated method stub
		if(this.player == null){
			isChecked = true;
			for(IBackgroundRectangle rectangle : this.neighbors){
				if(!rectangle.isChecked()){
					rectangle.checkPath();	
				}	
			}
			
		}
	}

	@Override
	public synchronized void disable() {
		// TODO Auto-generated method stub
		if(this.player == null){
			this.setScale(0.8f);
			this.setColor(Color.ABGR_PACKED_RED_SHIFT);
		}
	}

	@Override
	public synchronized void addIPlayer(IPlayer iPlayer) {
		// TODO Auto-generated method stub
		this.player = iPlayer;
		
		//Set its position and height and width to player inside its
		//We need cast it
		((Rectangle)this.player).setX(getX());
		((Rectangle)this.player).setY(getY());
		((Rectangle)this.player).setWidth(getWidth());
		((Rectangle)this.player).setHeight(getHeight());
		
		this.player.addIBackgroundRectabgle(this);
		
	}

	@Override
	public synchronized void removeIPlayer() {
		// TODO Auto-generated method stub
		this.player = null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public synchronized void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public boolean isTaken() {
		// TODO Auto-generated method stub
		
		//Return true if it has a player in it.
		return this.player != null;
	}

	@Override
	public synchronized void initToCheckPath() {
		// TODO Auto-generated method stub
		this.isChecked = true;
		for(IBackgroundRectangle rectangle : this.neighbors){
			rectangle.checkPath();	
		}
	}

	@Override
	public boolean isChecked() {
		// TODO Auto-generated method stub
		return this.isChecked;
	}

	@Override
	public synchronized void drawCross() {
		// TODO Auto-generated method stub
	   if(!this.isChecked){
		   setScale(0.8f);
		   setColor(Color.ABGR_PACKED_RED_CLEAR);
	   }
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		if(pSceneTouchEvent.isActionUp()){
			notifyObservers("TOUCHED");
		}
		return false;
	}
	
	@Override
	public void notifyObservers(Object object) {
		// TODO Auto-generated method stub
		for(Observer observer : this.observers){
			observer.update(this, object);
		}
	}

	@Override
	public void removeObservers(Observer observer) {
		// TODO Auto-generated method stub
		this.observers.remove(observer);
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		this.observers.add(observer);
	}
	
	
	
}
