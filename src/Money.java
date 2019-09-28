import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Money 
{
	ImageIcon img_money;
	ImageIcon img_now;
	int x;
	int y;
	int width;
	int height;
	boolean get_floor;
	boolean get_touch;
	
	int speed;
	int speedy;
	
	
	Money(int width,int height)
	{
		img_money = img_now = new ImageIcon(getClass().getResource("images/money.png"));
		
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
	//���䷽��
	void update()
	{
		y += speedy;
		if(get_floor == true)
		{
			again();
		}

	}
	//ͳ�ƽ��ս�ҵ��йط���,���ظı��ķ���
	int get_score_money(Character character,int score)
	{
		//�ж��Ƿ�ӵ����
		if(y + height < character.y+character.height && y + height >character.y  && x>character.x && x<character.x+character.width)
		{
			get_touch = true;
		}
		if(y >= 464)
		{
			get_floor = true;
		}
		//�ӵ���ҡ��÷֣����ӵ�ֵ��ʼ��,���ѭ������
		if(get_touch==true && get_floor==false)
		{
			score +=5;
			init();
			again();
			character.get_jump = true;
			
		}
		//������
		if(get_floor == true)
		{
			again();
			init();
		}
		return score;
	}
	
	
	//��ʼ�����������������������-10�������ٶ���������ӵ�������ж�ֵfalse
	void again()
	{
		x =(int)( Math.random()*10)*90;
		y = -5;
		init();
		img_now = img_money;
		speedy = (int)(Math.random()*6)+speed;
	}
	void init()
	{
		get_floor = false;
		get_touch = false;
	}
}
