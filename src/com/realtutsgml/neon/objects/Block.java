package com.realtutsgml.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.realtutsgml.neon.FrameWork.GameObject;
import com.realtutsgml.neon.FrameWork.ObjectId;
import com.realtutsgml.neon.FrameWork.Texture;
import com.realtutsgml.neon.window.Game;

public class Block extends GameObject{
	Texture tex=Game.getInstance();
	private int type;

	public Block(float x, float y,int type, ObjectId id) {
		super(x, y, id);
		this.type=type;
	}

	
	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
	
		if(type==0){
			g.drawImage(tex.block[0], (int)x, (int )y, null);
		}
		if(type==1){
			g.drawImage(tex.block[1], (int)x	, (int )y, null);
		}
	}

	public Rectangle getBounds() {
		  
		return new Rectangle((int)x,(int)y,32,32);
	}

	

}
