package com.realtutsgml.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.realtutsgml.neon.FrameWork.GameObject;
import com.realtutsgml.neon.FrameWork.ObjectId;
import com.realtutsgml.neon.objects.Block;
import com.realtutsgml.neon.objects.Flag;
import com.realtutsgml.neon.objects.Player;

public class Handler {

	public LinkedList<GameObject> object=new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level2=null;
	
	public Handler(Camera cam){
		this.cam=cam;
		BufferedImageLoader loader=new BufferedImageLoader();
		
		level2=loader.loadImage("/label2.png");
	}
	
	public void tick(){
		for(int i=0;i<object.size();i++){
			tempObject=object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g){
		for(int i=0;i<object.size();i++){
			tempObject=object.get(i);
			tempObject.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage image){
		int count =0;
		for(int xx=0;xx<image.getHeight()-1;xx++){
			for( int yy=0;yy<image.getWidth()-1;yy++){
				
				
				int pixel=image.getRGB(yy, xx);
				int red=(pixel>>16)& 0xff;
				int green=(pixel>>8)& 0xff;
				int blue=(pixel)& 0xff;
			
				
				if(red==255 && green==255 && blue==255){
					addObject(new Block(yy*32, xx*32, 1,ObjectId.Block));
				}
				if(red==0 && green==255 && blue==0){
					addObject(new Block(yy*32, xx*32, 0,ObjectId.Block));
				}
				if(red==0 && green==0 && blue==255){
					addObject(new Player(yy*32, xx*32, this,cam,ObjectId.player));
				}
				if(red==255 && green==222 && blue==0){
					addObject(new Flag(yy*32, xx*32,ObjectId.Flag));
				}
			}
		}
	}     
	
	public void switchLavel(){
		clearLevel();
		cam.setX(0);
		
		switch(Game.LEVEL){
		
		case 1:
			LoadImageLevel(level2);
			break;
		
		}
	}
	private void clearLevel(){
		object.clear();
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	
}
