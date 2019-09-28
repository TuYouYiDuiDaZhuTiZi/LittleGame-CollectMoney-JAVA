import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Shell
{
	//声明炮弹图像
	ImageIcon img_shell;
	//声明基本参数
	int x;
	int y = 460;
	int width = 40;
	int height = 80;
	boolean get_fire;
	boolean get_out;
	
	Shell()
	{
		img_shell = new ImageIcon(getClass().getResource("images/shell.png"));
		
	}
	void draw(Graphics g)
	{
		if(get_fire == true && get_out == false )
		{
			g.drawImage(img_shell.getImage(), x,y,width,height, null);
		}
	}	
	
	//获取发炮时坐标 
	void get_x(int x)
	{
		this.x = x + 30;
	}
	
	//炮弹位置坐标更新
	void update()
	{
		y -= 30;
		
		//炮弹飞出
		if(y <= 10 && y>0)
		{
			get_out = true;
		}
		if(y < 0)
		{
			init();
		}
		
	}
	void fire(Character character)
	{	
		get_fire = true;
		get_x(character.x);
	}
	void init()
	{
		get_fire = false;
		get_out = false;
		y = 460;
	}
	
	//
	
	
	
}
