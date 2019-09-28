import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Life
{
	int num_life = 3;
	ImageIcon img_now_life[]= new ImageIcon[3];
	ImageIcon img_life;
	ImageIcon img_die;
	
	Life()
	{
		img_life = new ImageIcon(getClass().getResource("images/life.png"));
		img_die = new ImageIcon(getClass().getResource("images/die.png"));
		
		for(int i = 0 ; i<3 ; i++)
		{
			img_now_life[i] = img_life;
		}
	}
	void draw(Graphics g)
	{
		//绘制生命值
		g.drawImage(img_now_life[0].getImage(),495,50,55,55, null);
		g.drawImage(img_now_life[1].getImage(),575,50,55,55, null);
		g.drawImage(img_now_life[2].getImage(),655,50,55,55, null);
	}
	
	int whether_die(Character character)
	{
		//生命值达到下限，失败。跳转失败页面，生命值初始化为3，生命值图案回归
		int state = 2;
		if(num_life==0 && character.get_boom == false)
		{
			state = 4;
			init();
		}
		return state;
	}
	void init()
	{
		num_life = 3;
		for(int i = 0 ; i<3 ; i++)
		{
			img_now_life[i] = img_life;
		}
	}
}
