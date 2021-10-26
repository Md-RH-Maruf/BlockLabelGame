package com.realtutsgml.neon.FrameWork;

import java.awt.image.BufferedImage;

import com.realtutsgml.neon.window.BufferedImageLoader;

public class Texture {

	SpriteSheet  bs,ps;

	private BufferedImage block_sheet=null;
	private BufferedImage player_sheet=null;

	public BufferedImage[] block=new BufferedImage[2];
	public BufferedImage[] player=new BufferedImage[30];
	public BufferedImage[] player_jump=new BufferedImage[24];
	
	public Texture(){

		BufferedImageLoader loader=new BufferedImageLoader();

		block_sheet=loader.loadImage("/block_sheet.png");
		player_sheet=loader.loadImage("/player_sheet.png");

		bs=new SpriteSheet(block_sheet);
		ps=new SpriteSheet(player_sheet);

		getTextures();
	}

	private void getTextures(){
		block[0]=bs.grabImage(1, 1, 32, 32);
		block[1]=bs.grabImage(2, 1, 32, 32);

		player[0]=ps.grabImage(1, 1, 60, 96);
		player[1]=ps.grabImage(2, 1, 60, 96);
		player[2]=ps.grabImage(3, 1, 60, 96);
		player[3]=ps.grabImage(4, 1, 60, 96);
		player[4]=ps.grabImage(5, 1, 60, 96);
		player[5]=ps.grabImage(6, 1, 60, 96);
		player[6]=ps.grabImage(7, 1, 60, 96);
		player[7]=ps.grabImage(8, 1, 60, 96);
		player[8]=ps.grabImage(9, 1, 60, 96);
		player[9]=ps.grabImage(10, 1, 60, 96);
		player[10]=ps.grabImage(11, 1, 60, 96);
		player[11]=ps.grabImage(12, 1, 60, 96);
		player[12]=ps.grabImage(13, 1, 60, 96);
		player[13]=ps.grabImage(14, 1, 60, 96);
		player[14]=ps.grabImage(15, 1, 60, 96);
		
		player[15]=ps.grabImage(15, 2, 60, 96);
		player[16]=ps.grabImage(1, 2, 60, 96);
		player[17]=ps.grabImage(2, 2, 60, 96);
		player[18]=ps.grabImage(3, 2, 60, 96);
		player[19]=ps.grabImage(4, 2, 60, 96);
		player[20]=ps.grabImage(5, 2, 60, 96);
		player[21]=ps.grabImage(6, 2, 60, 96);
		player[22]=ps.grabImage(7, 2, 60, 96);
		player[23]=ps.grabImage(8, 2, 60, 96);
		player[24]=ps.grabImage(9, 2, 60, 96);
		player[25]=ps.grabImage(10, 2, 60, 96);
		player[26]=ps.grabImage(11, 2, 60, 96);
		player[27]=ps.grabImage(12, 2, 60, 96);
		player[28]=ps.grabImage(13, 2, 60, 96);
		player[29]=ps.grabImage(14, 2, 60, 96);
		
		player_jump[0]=ps.grabImage(6, 3, 60, 96);
		player_jump[1]=ps.grabImage(6, 4, 60, 96);
		
		
		
	}
}
