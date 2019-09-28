import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bird
{
	ImageIcon img_bird_left;
	ImageIcon img_bird_right;
	ImageIcon img_bird_fall;
	ImageIcon img_bird;
	
	int x = (int)(Math.random()*800);
	int y = 40;
	int width = 140;
	int height = 100;
	
	int detx = 5;
	int dety = 10;
	
	int get_bird = 0;
	int come;
	
	
	boolean get_dead;
	boolean get_picked;
	boolean get_floor;
	boolean get_appear;
	
	Bird()
	{
		come =0;
		img_bird_left = new ImageIcon(getClass().getResource("images/bird_left.png"));
		img_bird_right = new ImageIcon(getClass().getResource("images/bird_right.png"));
		img_bird_fall = new ImageIcon(getClass().getResource("images/bird_fall.png"));
		img_bird = img_bird_right;
	}
	void draw(Graphics g,Character character)
	{
		if( get_picked == false && get_appear == true)
		{
			g.drawImage(img_bird.getImage(),x,y,width,height, null);
		}
	}
	void update()
	{
		
		//
		come++;
		if(come%300>100 && come%300<299)
		{
			get_appear = true;
		}
		
		//Ë®Æ½·ÉÐÐ
		if(get_appear == true && get_dead == false && get_floor ==false)
		{
			x += detx;
			fly_direction();
		}
		//ÅÐ¶ÏÂäµØ
		if(y >=430)
		{
			get_floor = true;
		}
		//´¹Ö±½µÂä
		if(get_dead == true && get_floor == false)
		{
			y += dety;	
		}
		
	}
	void get_fired(Shell shell)
	{
		//ÅÚµ¯»÷ÖÐÄñ,Ö´ÐÐÒ»´Î
		if(shell.x  >= this.x && shell.x + shell.width <= this.x + this.width && shell.y > this.y && shell.y < this.y + this.height)
		{
			get_dead = true;//ÄñËÀÁË
			shell.init();//ÅÚµ¯³õÊ¼»¯
			img_bird = img_bird_fall;//Äñ×ª»»Îª½µÂäÍ¼Æ¬
		}
	}
	//¼ñÄñ
	int picked(Character character,int score)
	{
		if(x -20 < character.x && x + width > character.x + character.width +20&& y + 20 >character.y && y + 20 < character.y +character.height)
		{
			get_picked = true;
		}
		if(get_dead == true && get_picked == true)
		{
			score += 50;
			get_bird++;
			again();
		}
		return score;
	}
	//·ÉÐÐ·½ÏòÅÐ¶Ï
	void fly_direction()
	{
		if(x + width > 899)
		{
			detx = -detx;
		}
		else if(x < 1)
		{
			detx = -detx;
			
		}
		if(detx < 0)
		{
			img_bird = img_bird_left;
		}
		else
		{
			img_bird = img_bird_right;
		}
	}
	void init()
	{
		again();
		get_bird = 0;
	}
	void again()
	{
		y = 40;
		x = (int)(Math.random()*720);
		detx = 5;
		fly_direction();
		come = 0;
		get_appear = false;
		get_picked = false;
		get_dead = false;
		get_floor = false;
	}

}
