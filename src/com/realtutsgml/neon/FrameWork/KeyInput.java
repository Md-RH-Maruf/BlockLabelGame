package com.realtutsgml.neon.FrameWork;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.realtutsgml.neon.objects.Bullet;
import com.realtutsgml.neon.window.Handler;

public class KeyInput extends KeyAdapter{

	Handler handler;

	public KeyInput(Handler handler){
		this.handler=handler;
	}

	public void keyPressed(KeyEvent e){

		int key=e.getKeyCode();

		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ObjectId.player){
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key==KeyEvent.VK_UP && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-20);
				}
				if(key==KeyEvent.VK_SPACE){
					handler.addObject(new Bullet(tempObject.getX()+20, tempObject.getY()+25, ObjectId.Bullet,tempObject.getFacing()*10));
				}
			}
		}

		if(key==KeyEvent.VK_ESCAPE){
			System.exit(1);
		}

	}
	public void keyReleased(KeyEvent e){

		int key=e.getKeyCode();

		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ObjectId.player){
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key==KeyEvent.VK_UP){
					tempObject.setVelY(0);
				}
			}
		}
	}

}
