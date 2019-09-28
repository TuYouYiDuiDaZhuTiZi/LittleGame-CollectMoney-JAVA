import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bomb 
{
	ImageIcon img_bomb;
	ImageIcon img_now;
	
	int x;
	int y;
	int width;
	int height;
	
	boolean get_touch;
	boolean get_floor;
	
	int speedy;
	int speed;
	
	static int a = 0;
	
	
	Bomb(int width,int height)
	{
		img_bomb = img_now = new ImageIcon(getClass().getResource("images/bomb.png"));
		this.width = width;
		this.height = height;
		this.x =(int)( Math.random()*10)*90;
		this.y = 0;
		
	}
	void get_speed(int speed)
	{
		this.speed = speed;
		speedy = (int)(Math.random()*6)+speed;
	}
	void draw(Graphics g)
	{
		g.drawImage(img_now.getImage(),x, y, width, height, null);
	}
	//炸弹对生命值的改变
	Life get_score_bomb(Character character,Life life)
	{
		//判断是否接到炸弹
		if(y<character.y+character.width && y>character.y  && x>character.x && x<character.x+character.width)
		{
			get_touch = true;
		}
		//判断是否落地
		if(y >= 464)
		{
			get_floor = true;
		}
		
		//接到炸弹、失去生命，将生命值更新,炸弹循环落下
		if(get_touch==true && get_floor==false && character.get_boom == false)
		{
			//失去生命
			life.num_life --;
			life.img_now_life[2-life.num_life] = life.img_die;
			
			get_floor = false;
			get_touch = false;
			again();
			character.get_boom = true;
			character.speed = 0;
			
			
		}
		
		//炸弹落地
		if(get_floor == true)
		{
			again();
			
		}
		return life;
	}
	
	//下落方法
	void update()
	{
		y +=speedy ;
		//落地
		if(get_floor == true)
		{
			this.again();
		}
	}
	//初始化：横坐标随机数，纵坐标-10，下落速度随机数，接到和落地判断值false
	void again()
	{
		x =(int)( Math.random()*10)*90;
		y = -10;
		img_now = img_bomb;
		speedy = (int)(Math.random()*6)+speed;
		init();
	}
	void init()
	{
		get_floor = false;
		get_touch = false;
	}
}
