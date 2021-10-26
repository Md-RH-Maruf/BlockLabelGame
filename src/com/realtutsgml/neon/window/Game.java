package com.realtutsgml.neon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.realtutsgml.neon.FrameWork.KeyInput;
import com.realtutsgml.neon.FrameWork.ObjectId;
import com.realtutsgml.neon.FrameWork.Texture;
import com.realtutsgml.neon.objects.Block;
import com.realtutsgml.neon.objects.Flag;
import com.realtutsgml.neon.objects.Player;

public class Game extends Canvas implements Runnable{

	
	private static final long serialVersionUID = -6261436164361361187L;

	private boolean running =false;
	private Thread thread;
	
	public static int WIDTH,HEIGHT;
	public BufferedImage level=null,cloud=null;
	
	public static int LEVEL=1;
	Camera cam;
	//Object
	Handler handler;
	static Texture tex;
	
	private void init(){
		
		WIDTH=getWidth();
		HEIGHT=getHeight();
		
		tex=new Texture();
		
		BufferedImageLoader loader=new BufferedImageLoader();
		level=loader.loadImage("/label1.png");
		
		cloud=loader.loadImage("/cloud.png");
		cam=new Camera(0, 0);
		handler= new Handler(cam);
		
		
		//handler.addObject(new Player(100, 100, handler,ObjectId.player));
		handler.LoadImageLevel(level);
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
		
	}
	
	public synchronized void start(){
		
		if(running){
			return;
		}
		
		running =true;
		thread =new Thread(this);
		
		thread.start();
		
	}
	public void run(){
		
		init();
	
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int  updates=0;
		int frames=0;
		while(running){
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer>500){
				timer+=1000;
				//System.out.println("Fps: "+frames+" Ticks: "+updates);
				frames=0;
				updates=0;
				
			}
			
			//thread.stop();
		}
		
		
	}
	
	public void tick(){
		handler.tick();
		for(int i=0;i<handler.object.size();i++){
			if(handler.object.get(i).getId()==ObjectId.player){
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	public void render(){
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		Graphics2D g2d=(Graphics2D)g;
		////////////////////////////////
		//Draw Here
		g.setColor(new Color(25,191,224));
		g.fillRect(0, 0, getWidth(), getHeight());
		g2d.translate(cam.getX(), cam.getY());
		for(int xx=30;xx<(cloud.getWidth()*5);xx+=cloud.getWidth()){
			g.drawImage(cloud, xx, 100, this);
		}
		
		
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());
		///////////////////////////////
		g.dispose();
		bs.show();
	}
	
	 
	
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String args[]){
		new Window(800,600,"Neon Platform Game Prototype",new Game());

	}
}
