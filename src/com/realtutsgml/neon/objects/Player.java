package com.realtutsgml.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.realtutsgml.neon.FrameWork.GameObject;
import com.realtutsgml.neon.FrameWork.ObjectId;
import com.realtutsgml.neon.FrameWork.Texture;
import com.realtutsgml.neon.window.Animation;
import com.realtutsgml.neon.window.Camera;
import com.realtutsgml.neon.window.Game;
import com.realtutsgml.neon.window.Handler;

public class Player extends GameObject{

	private float width=48,height=96;
	private float gravity=0.5f,MAX_SPPED=10;
	
	
	private  Camera cam;
	private Handler handler;

	Texture tex=Game.getInstance();

	private Animation playerWalkRight,playerWalkLeft,playerJump;

	public Player(float x, float y,Handler handler,Camera cam ,ObjectId id) {
		super(x, y, id);
		this.handler=handler;
		this.cam=cam;
		playerWalkRight=new Animation(1,
				tex.player[1],
				tex.player[2],
				tex.player[3],
				tex.player[4],
				tex.player[5],
				tex.player[6],
				tex.player[7],
				tex.player[8],
				tex.player[9],
				tex.player[10],
				tex.player[11],
				tex.player[12],
				tex.player[13],
				tex.player[14]);
		
		playerWalkLeft=new Animation(1,
				tex.player[29],
				tex.player[28],
				tex.player[27],
				tex.player[26],
				tex.player[25],
				tex.player[24],
				tex.player[23],
				tex.player[22],
				tex.player[21],
				tex.player[20],
				tex.player[19],
				tex.player[18],
				tex.player[17],
				tex.player[16]);
		
		
	}

	public void tick(LinkedList<GameObject> object) {

		x+=velX;
		y+=velY;
		
		if(velX<0) facing=-1;
		else if(velX>0) facing=1;

		if(falling||jumping){
			velY+=gravity;
			if(velY>MAX_SPPED)
				velY=MAX_SPPED;

		}
		Collision(object);
			playerWalkRight.runAnimation();
			playerWalkLeft.runAnimation();	
		
	}

	private void Collision(LinkedList<GameObject> object){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);

			if(tempObject.getId()==ObjectId.Block){

				if(getBoundsTop().intersects(tempObject.getBounds())){
					y=tempObject.getY()+32;
					velY=0;

				}

				if(getBounds().intersects(tempObject.getBounds())){
					y=tempObject.getY()-height;
					velY=0;
					falling=false;
					jumping=false;
				}
				else
					falling=true;

				if(getBoundsRight().intersects(tempObject.getBounds())){
					x=tempObject.getX()-width;

				}

				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x=tempObject.getX()+28;

				}
			}else if(tempObject.getId()==ObjectId.Flag)
			{
				if(getBounds().intersects(tempObject.getBounds())){
					handler.switchLavel();
				}
				
			}
			}
	}
	public void render(Graphics g) {

		if(jumping){

			if(facing==1){
				g.drawImage(tex.player_jump[0], (int)x, (int)y, 60,96,null);
			}else if(facing==-1){
				g.drawImage(tex.player_jump[1], (int)x, (int)y, 60,96,null);
			}
		}else{
			if(velX!=0){
				if(facing==1)
					playerWalkRight.drawAnimation(g, (int)x,(int) y, 60, 96);
				else
					playerWalkLeft.drawAnimation(g, (int)x,(int) y, 60, 96);
			}else{
				if(facing==1)
					g.drawImage(tex.player[0],(int) x, (int)y, null);
				else if(facing==-1)
					g.drawImage(tex.player[15],(int) x, (int)y, null);
			}
		}
		
			
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)/2),(int)y+(int)height/2,(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsTop() {

		return new Rectangle((int) ((int)x+(width/2)/2),(int)y,(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsLeft() {

		return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
	}
	public Rectangle getBoundsRight() {

		return new Rectangle((int) ((int)x+(width-5)),(int)y+5,(int)5,(int)height-10);
	}

}
