
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Button
{
	//三个图像属性
	ImageIcon img_b1;
	ImageIcon img_b2;
	ImageIcon img_now = img_b1;
	
	//光标是否符合按钮位置属性
	boolean result = false;
	
	//坐标及大小属性
	int x;
	int y;
	int width;
	int height;
	
	void draw(Graphics g)
	{
		g.drawImage(img_now.getImage(),x,y,width,height, null);
	}
	//构造方法，以便在实例化时引入坐标及大小参数
	//因为局部变量总是屏蔽普通变量，所以加上this才表示普通变量
	Button(int x,int y,int width,int height)
	{
		this.width = width; 
		this.height = height; 
		this.x = x;
		this.y = y;
	
	}

	//判断鼠标位置是否在按钮位置，返回true或false，可用于点击和移动高亮
	boolean touch(int mouse_x,int mouse_y)
	{
		if(mouse_x>x&&mouse_x<x+width &&mouse_y>y&&mouse_y<y+height)
		{
			result = true;	
		}
		else
		{
			result = false;
		}
		return result;
		
	}
	//判断是否可用于按钮高亮（移动使切换图片）
	void change_img(boolean result)
	{
		if(result == true)
		{
			img_now = img_b2;
		}
		else
		{
			img_now = img_b1;
		}
	}

	
}
