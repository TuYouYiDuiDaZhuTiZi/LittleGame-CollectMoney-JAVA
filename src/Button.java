
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Button
{
	//����ͼ������
	ImageIcon img_b1;
	ImageIcon img_b2;
	ImageIcon img_now = img_b1;
	
	//����Ƿ���ϰ�ťλ������
	boolean result = false;
	
	//���꼰��С����
	int x;
	int y;
	int width;
	int height;
	
	void draw(Graphics g)
	{
		g.drawImage(img_now.getImage(),x,y,width,height, null);
	}
	//���췽�����Ա���ʵ����ʱ�������꼰��С����
	//��Ϊ�ֲ���������������ͨ���������Լ���this�ű�ʾ��ͨ����
	Button(int x,int y,int width,int height)
	{
		this.width = width; 
		this.height = height; 
		this.x = x;
		this.y = y;
	
	}

	//�ж����λ���Ƿ��ڰ�ťλ�ã�����true��false�������ڵ�����ƶ�����
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
	//�ж��Ƿ�����ڰ�ť�������ƶ�ʹ�л�ͼƬ��
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
