import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//��ͣ��
//���������ܽ������������ը�����ʣ����Ǳ��ʲô�ģ�
//		��	���һ��ʼ�������������ٲ��ȶ���һ���ٶ���
//		��	�س����Զ���ʼ�����¿�ʼ
//		��	���Խ�練Ӧ
//		��	��ɫÿ�ο�ʼ�Լ���
//		��	��ɫ�ж������� 

//		��	��ͷ���ֲ���ʧ��ָ��
//		��	��ͣ���ܵ�ʵ��
 //�˳�����

public class GamePanel extends JPanel implements KeyListener,MouseListener,MouseMotionListener
{
	//����ʱ���¼
	Timer timer= new Timer();
	
	//�����������
	int state = 1;
	int guid_count;
	int  go = 1;
	
	//���������ָ
	ImageIcon img_finger_now;
	ImageIcon img_finger;
	ImageIcon img_finger_2;
	
	//��������
	Character character;
	
	//��������ͼ��
	ImageIcon img_money_beginning;
	ImageIcon img_character_beginning;
	ImageIcon img_state2;
	ImageIcon img_guid;
	ImageIcon img_state3;
	ImageIcon img_state4;
	ImageIcon img_state5;
	
	//����������
	Life life;
	
	//�����÷֡�����ֵ
	int score = 0;
	
	
	//������ť
	Button b_help;
	Button b_easy;
	Button b_diff;
	Button b_retu1;
	Button b_retu2;
	Button b_stop;
	
	//������Һ�ը����Ͷ���ڵ�������ÿ�γ��ָ�������
	Money money[]= new Money[4];
	Bomb bomb[] = new Bomb[4];
	Shell shell[] = new Shell[4];
	Bird bird = new Bird();
	
	//������ҡ�ը��ÿ�εĸ������ޡ����ٶȿ���
	int num ;
	int num2;

	int speed;
	
	//���캯��
	GamePanel()
	{
		//ʵ�������Ｐͼ��
		character = new Character();
		
		//ʵ�������桢�����ͼ��
		img_money_beginning = new ImageIcon(getClass().getResource("images/money_beginning.gif"));
		img_character_beginning = new ImageIcon(getClass().getResource("images/character_beginning.gif"));
		//img_state1 = new ImageIcon(getClass().getResource("images/state1.jpg"));
		img_state2 = new ImageIcon(getClass().getResource("images/state2.jpg"));
		img_state3 = new ImageIcon(getClass().getResource("images/state3.jpg"));
		img_state4 = new ImageIcon(getClass().getResource("images/state4.jpg"));
		img_state5 = new ImageIcon(getClass().getResource("images/state5.jpg"));
		img_guid = new ImageIcon(getClass().getResource("images/guid.png"));
		
		//ʵ�������ͼ��
		img_finger = new ImageIcon(getClass().getResource("images/finger.png"));
		img_finger_2 = new ImageIcon(getClass().getResource("images/finger_2.png"));
		img_finger_now = img_finger;
		
		//ʵ������ť��
		b_easy = new Button(125,290,156,50);
		b_diff = new Button(125,367,156,50);
		b_help = new Button(318,367,156,50);
		b_retu1 = new Button(648,490,156,50);
		b_retu2 = new Button(800,50,50,50);
		b_stop = new Button(730,50,50,50);
		
		//ʵ�������а�ťͼ�񣨰�������ǰ�͸�����
		b_easy.img_b1 = b_easy.img_now = new ImageIcon(getClass().getResource("images/b_easy.png"));
		b_diff.img_b1 = b_diff.img_now = new ImageIcon(getClass().getResource("images/b_diff.png"));
		b_help.img_b1 = b_help.img_now = new ImageIcon(getClass().getResource("images/b_help.png"));
		b_retu1.img_b1 = b_retu1.img_now =  new ImageIcon(getClass().getResource("images/b_retu1.png"));
		b_retu2.img_b1 = b_retu2.img_now =  new ImageIcon(getClass().getResource("images/b_retu2.png"));
		b_stop.img_b1 = b_stop.img_now =  new ImageIcon(getClass().getResource("images/b_stop.png"));
		
		//����
		b_easy.img_b2 = new ImageIcon(getClass().getResource("images/b_easy_2.png"));
		b_diff.img_b2 = new ImageIcon(getClass().getResource("images/b_diff_2.png"));
		b_help.img_b2 = new ImageIcon(getClass().getResource("images/b_help_2.png"));
		b_retu1.img_b2 = new ImageIcon(getClass().getResource("images/b_retu1_2.png"));
		b_retu2.img_b2 = new ImageIcon(getClass().getResource("images/b_retu2_2.png"));
		b_stop.img_b2 = new ImageIcon(getClass().getResource("images/b_stop_2.png"));
		
		//ʵ����������
		life = new Life();
		
		for(int i = 0 ; i< 4 ; i++)
		{
			money[i] = new Money(56,56);
		}
		//ʵ����ը�������
		for(int i = 0 ; i<4 ; i++)
		{
			bomb[i] = new Bomb(50,65);
		}
		//ʵ�����ڵ�����
		for(int i = 0 ; i<4 ; i++)
		{
			shell[i] = new Shell();
		}
		
		//�������
		this.setFocusable(true);
		//�����ꡢ����ƶ������̼���
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//ʵ������ʱ��
		timer.schedule(new MyTask(),0, 20);
		
	}
	

	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//��������ͼ����
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(img_finger_now.getImage(), new Point(), null));
		
		if(state ==1)
		{
			//������ҳ���������ť
			//g.drawImage(img_state1.getImage(), 0,0,900,600, null);
			g.drawImage(img_character_beginning.getImage(), 0,0,900,600, null);
			g.drawImage(img_money_beginning.getImage(), 0,0,900,600, null);
			
			b_easy.draw(g);
			b_diff.draw(g);
			b_help.draw(g);
				
		}
		
		
		else if(state ==2)
		{
			//������Ϸҳ��
			g.drawImage(img_state2.getImage(),0,0,900,600, null);
			if(guid_count < 350)
			{
				g.drawImage(img_guid.getImage(),0,0,900,600, null);
			}
			//���ƽ�ɫ
			character.draw(g);
			
			//���ƽ��
			for(int i = 0 ; i<num ; i++)
			{
				money[i].draw(g);
			}
			//����ը��
			for(int i = 0 ; i<num2 ; i++)
			{				
				bomb[i].draw(g);
			}	
			
			//���������ڵ�
			for(int i = 0 ; i< 4 ; i++)
			{
				shell[i].draw(g);
			}
			
			//������������ֵ
			life.draw(g);
			
			//������
			bird.draw(g,character);
			
			//���Ʒ��ذ�ť
			b_retu2.draw(g);
			b_stop.draw(g);
			
			//���Ƶ÷�
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("�µ�С����Сѧ��",Font.BOLD,30));
			g.drawString("�������" + score, 50, 70);
			g.drawString("��ʯ����" + bird.get_bird, 50, 130);
			
		}
		else if(state ==3)
		{
			//���ư����ͷ��ذ�ť
			g.drawImage(img_state3.getImage(),0,0,900,600, null);
			b_retu1.draw(g);
		}
		else if(state ==4)
		{
			//����ʧ�ܺͷ���
			g.drawImage(img_state4.getImage(),0,0,900,600, null);
			b_retu1.draw(g);
		}
		else if(state ==5)
		{
			//���Ƴɹ��ͷ���
			
			g.drawImage(img_state5.getImage(),0,0,900,600, null);
			b_retu1.draw(g);
		}
	}

	
	class MyTask extends TimerTask
	{
		public void run()
		{
			if(go == 1)
			{
				if(state == 2)	
				{
					guid_count++;
					//��Һ�ը��������ʼ��
					character.update();
					//���λ�ø��£������ѭ��
					for(int i  = 0 ; i<num ; i++)
					{
						money[i].update();
						
					}
					//���λ�ø��£������ѭ��
					for(int i = 0 ; i<num2 ; i++)
					{
						bomb[i].update();
					}
					
					//�ڵ�λ�ø���
					for(int i = 0 ; i<4 ; i++)
					{
						if(shell[i].get_fire == true)
						{
							shell[i].update();
						}
					}
					//
					bird.update();
					
					//���µ÷֣�ʹ�õ÷ַ���
					get_score();
				}
				repaint();
				
			}
		}	
	}
	//ͳ�Ƶ÷֡��޸�����ֵ
	void get_score()
	{
		//�����н��ͳ�Ƶ÷�
		for(int i = 0; i <num ; i++)	
		{
			score = money[i].get_score_money(character,score);
			
		}
		//������ը��������ֵ��ͳ��
		for(int i = 0; i<num2 ; i++)	
		{
			life = bomb[i].get_score_bomb(character,life);
			
		}
		//�Ƿ������
		for(int i = 0 ; i< 4 ; i++)
		{
			bird.get_fired(shell[i]);
		}
		//����
		score = bird.picked(character, score);
		//�Ƿ�����
		state = life.whether_die(character);
		//�÷ִﵽ���ޣ���ʤ
		if(score >= 400)
		{
			state = 5;
		}
		
	}
	void everything_get_speed()
	{
		for(int i = 0 ;i < num ; i++)
		{	
			money[i].get_speed(speed);
		}
		for(int i = 0 ; i < num2 ; i++)
		{
			bomb[i].get_speed(speed);
		}
	}
	//��ģʽ��ʼ
	void easy_beginning()
	{
		num = 3;
		num2 = 2;	
		state = 2;
		speed = 6;
		b_easy.result = false;
		everything_init();
		everything_get_speed();
		
	}
	//����ģʽ��ʼ
	void diff_beginning()
	{
		state = 2;
		num = 4;
		num2 = 3;
		speed = 8;
		b_diff.result = false;
		everything_init();
		everything_get_speed();
	}
	//������ֵ��ʼ��
	void everything_init()
	{
		score = 0;
		life.init();
		bird.init();
		character.init();
		for(int i = 0 ; i< 4 ; i++)
		{
			shell[i].init();
		}	
	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	public void keyPressed(KeyEvent e)
	{
		if(state == 2)
		{
			//�����������ɫ����
			if( e.getKeyCode() == KeyEvent.VK_LEFT )
			{
				
				
				if(character.get_boom == false)
				{
					//��������90
					character.img_now = character.img_left;
					if(character.x>=30)
					{
						character.speed = -10;
					}
				}
				
				
			}
			//�����Ҽ�����ɫ����
			else if( e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(character.get_boom == false)
				{
					
					character.img_now = character.img_right;
					if(character.x + character.width <=870)
					{
						character.speed = 10;
					}
				}
			}
			//���̿ո���������ڵ�.����ж�Ϊtrue,�ڵ��±�����1��ʼ����4��ѭ��
			else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				for(int i = 0; i < 4 ; i++)
				{
					if(shell[i].get_fire == false)
					{					
						//
						shell[i].fire(character);
						break;
					}
				}
				
			}
			else if(e.getKeyCode() == KeyEvent.VK_Z)
			{
				go = -go;
			}
		}
		if(state == 1 || state == 4 || state == 5)
		{
			if( e.getKeyCode() == KeyEvent.VK_ENTER )
			{
				easy_beginning();
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(state == 2)
		{
			//��������ɿ�����ɫͣ��
			if( character.get_boom == false && e.getKeyCode() == KeyEvent.VK_LEFT)
			{

				//��������90
				character.img_now = character.img_left;
				character.speed = 0;
			}
			//�����Ҽ��ɿ�����ɫͣ��
			else if( character.get_boom == false && e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				character.img_now = character.img_right;
				character.speed = 0;
				
			}
		}
		
	}
	
	
	public void mouseClicked(MouseEvent e) {

	}

//������
	public void mousePressed(MouseEvent e) 
	{
		img_finger_now = img_finger_2;
		//��ҳ�棬�򵥡����ѡ�����������ť�������ť����жϹ�false
		if(state == 1)
		{
			//�򵥰�ť��������Ϸ���棬��ҡ�ը��������ʼ�������ٶȳ�ʼ��
			if(b_easy.touch(e.getX(), e.getY())==true)
			{
				easy_beginning();
			}
			//���Ѱ�ť��������Ϸ���棬��ҡ�ը���������ӣ����ٶ�����
			else if(b_diff.touch(e.getX(), e.getY())==true)
			{
				diff_beginning();
			}
			//������ť����ת����������
			else if(b_help.touch(e.getX(), e.getY())==true)
			{
				state = 3;
				b_help.result = false;
			}
		}
		
		//��Ϸ���еķ��ذ�ť����ת��ҳ�棬��ť����жϹ�false,����ֵ������ڵ����ж�ֵ��ʼ��
		else if(state == 2)
		{
			if(b_retu2.touch(e.getX(), e.getY()))
			{
				
				state = 1;
				b_retu2.result = false;
				everything_init();
			}
			else if(b_stop.touch(e.getX(), e.getY()))
			{	
				go = -go;
				
				
			}
		}
		
		//�����ķ��ذ�ť����ת��ҳ�棬��ť����жϹ�false
		else if(state == 3)
		{
			if(b_retu1.touch(e.getX(), e.getY())==true)
			{
				state = 1;
				b_retu1.result = false;
				
			}
			
		}
		//ʧ�ܵķ��ذ�ť�Ĵ�����ת��ҳ�棬�������÷֡����ڵ���ʼ������ť������жϹ�false
		else if(state == 4)
		{
			if(b_retu1.touch(e.getX(), e.getY())==true)
			{
				state = 1;
				b_retu1.result = false;
				everything_init();
			}
			
			
		}
		//�ɹ��ķ��ذ�ť�Ĵ�����ת��ҳ�棬�÷ֹ��㣬��ť����жϹ�false
		else if(state == 5)
		{
			if(b_retu1.touch(e.getX(), e.getY())==true)
			{
				state = 1;
				b_retu1.result = false;
				everything_init();
			}
			
		}
	}


	public void mouseReleased(MouseEvent e) 
	{
		img_finger_now = img_finger;
		
	}


	public void mouseEntered(MouseEvent e) {

		
	}


	public void mouseExited(MouseEvent e) 
	{

	}




	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		//����ƶ���ͼƬ�任����
		if(state == 1)
		{
			b_easy.change_img(b_easy.touch(e.getX(),e.getY()));
			b_diff.change_img(b_diff.touch(e.getX(),e.getY()));
			b_help.change_img(b_help.touch(e.getX(),e.getY()));
		}
		
		else if(state == 2)
		{
			b_retu2.change_img(b_retu2.touch(e.getX(),e.getY()));
		}
		else if(state == 3||state == 4 || state == 5)
		{
			b_retu1.change_img(b_retu1.touch(e.getX(), e.getY()));
		}
	}
}