import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Character
{
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	int speedy = -9;
	boolean get_appear;
	boolean get_jump;
	boolean get_boom;
	boolean get_change;
	int boom_count;
	
	ImageIcon img_now;
	ImageIcon img_left;
	ImageIcon img_right;
	ImageIcon img_act_left;
	ImageIcon img_before;
	final ImageIcon img_boom;
	
	Character()
	{
		img_left = new ImageIcon(getClass().getResource("images/character_left.png"));
		img_right = new ImageIcon(getClass().getResource("images/character_right.png"));
		img_now = img_right;
		img_boom = new ImageIcon(getClass().getResource("images/boom.png"));
		
		width = 100;
		height = 120;
		x = 450 - 100;
		y = 515 - 120;
	}
	void draw(Graphics g)
	{
		
		g.drawImage(img_now.getImage(), x, y, width, height, null);//
	}
	
	
	void update()
	{
		//两边限制，一旦超越限制，则重新赋值为限制内数值
		if(x>=0.1 && x<=860.1 && get_boom == false)
		{
			x += speed;
		}
		else if(x<0.1)
		{
			x = 1;
		}
		else if(x>860.1)
		{
			x = 860;
		}
		
		if(get_jump==true && y <= 515-120 )
		{
			y += speedy;
			speedy +=2;
			
		}
		else if(y > 515-120 && get_jump==true)
		{
			get_jump = false;
			 y = 515-120;
			 speedy = -9; 
		}
		
		//
		
		
		if(get_boom == true)
		{
			
			boom_count++;
			
			if(boom_count<100.1 && get_change == false)
			{
				img_before = img_now;
				img_now = img_boom;
				
				get_change = true;
				
				
			}
			else if(boom_count>=100.1 && get_change == true)
			{
				img_now = img_before;
				
				boom_count = 0;
				get_boom = false;
				
				get_change = false;
				
			}
		}
	}
	void init()
	{
		x = 450 - 100;
		speed = 0;
		img_now = img_right;
		get_boom = false;
	}
}
