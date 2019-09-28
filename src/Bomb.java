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
	//ը��������ֵ�ĸı�
	Life get_score_bomb(Character character,Life life)
	{
		//�ж��Ƿ�ӵ�ը��
		if(y<character.y+character.width && y>character.y  && x>character.x && x<character.x+character.width)
		{
			get_touch = true;
		}
		//�ж��Ƿ����
		if(y >= 464)
		{
			get_floor = true;
		}
		
		//�ӵ�ը����ʧȥ������������ֵ����,ը��ѭ������
		if(get_touch==true && get_floor==false && character.get_boom == false)
		{
			//ʧȥ����
			life.num_life --;
			life.img_now_life[2-life.num_life] = life.img_die;
			
			get_floor = false;
			get_touch = false;
			again();
			character.get_boom = true;
			character.speed = 0;
			
			
		}
		
		//ը�����
		if(get_floor == true)
		{
			again();
			
		}
		return life;
	}
	
	//���䷽��
	void update()
	{
		y +=speedy ;
		//���
		if(get_floor == true)
		{
			this.again();
		}
	}
	//��ʼ�����������������������-10�������ٶ���������ӵ�������ж�ֵfalse
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
