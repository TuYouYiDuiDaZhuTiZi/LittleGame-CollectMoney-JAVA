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

//暂停键
//交互：接受金币上跳，接受炸弹晕厥（还是别的什么的）
//		√	金币一开始很慢，后来加速并稳定在一个速度上
//		√	回车键自动开始或重新开始
//		√	鸟的越界反应
//		√	角色每次开始自己走
//		√	角色行动变灵敏 

//		√	开头出现并消失的指引
//		√	暂停功能的实现
 //退出程序

public class GamePanel extends JPanel implements KeyListener,MouseListener,MouseMotionListener
{
	//声明时间记录
	Timer timer= new Timer();
	
	//声明界面控制
	int state = 1;
	int guid_count;
	int  go = 1;
	
	//声明光标手指
	ImageIcon img_finger_now;
	ImageIcon img_finger;
	ImageIcon img_finger_2;
	
	//声明人物
	Character character;
	
	//声明界面图像
	ImageIcon img_money_beginning;
	ImageIcon img_character_beginning;
	ImageIcon img_state2;
	ImageIcon img_guid;
	ImageIcon img_state3;
	ImageIcon img_state4;
	ImageIcon img_state5;
	
	//声明生命类
	Life life;
	
	//声明得分、生命值
	int score = 0;
	
	
	//声明按钮
	Button b_help;
	Button b_easy;
	Button b_diff;
	Button b_retu1;
	Button b_retu2;
	Button b_stop;
	
	//声明金币和炸弹、投掷炮弹，及其每次出现个数上限
	Money money[]= new Money[4];
	Bomb bomb[] = new Bomb[4];
	Shell shell[] = new Shell[4];
	Bird bird = new Bird();
	
	//声明金币、炸弹每次的个数上限、加速度控制
	int num ;
	int num2;

	int speed;
	
	//构造函数
	GamePanel()
	{
		//实例化人物及图像
		character = new Character();
		
		//实例化界面、并添加图像
		img_money_beginning = new ImageIcon(getClass().getResource("images/money_beginning.gif"));
		img_character_beginning = new ImageIcon(getClass().getResource("images/character_beginning.gif"));
		//img_state1 = new ImageIcon(getClass().getResource("images/state1.jpg"));
		img_state2 = new ImageIcon(getClass().getResource("images/state2.jpg"));
		img_state3 = new ImageIcon(getClass().getResource("images/state3.jpg"));
		img_state4 = new ImageIcon(getClass().getResource("images/state4.jpg"));
		img_state5 = new ImageIcon(getClass().getResource("images/state5.jpg"));
		img_guid = new ImageIcon(getClass().getResource("images/guid.png"));
		
		//实例化鼠标图形
		img_finger = new ImageIcon(getClass().getResource("images/finger.png"));
		img_finger_2 = new ImageIcon(getClass().getResource("images/finger_2.png"));
		img_finger_now = img_finger;
		
		//实例化按钮类
		b_easy = new Button(125,290,156,50);
		b_diff = new Button(125,367,156,50);
		b_help = new Button(318,367,156,50);
		b_retu1 = new Button(648,490,156,50);
		b_retu2 = new Button(800,50,50,50);
		b_stop = new Button(730,50,50,50);
		
		//实例化所有按钮图像（包括高亮前和高亮后）
		b_easy.img_b1 = b_easy.img_now = new ImageIcon(getClass().getResource("images/b_easy.png"));
		b_diff.img_b1 = b_diff.img_now = new ImageIcon(getClass().getResource("images/b_diff.png"));
		b_help.img_b1 = b_help.img_now = new ImageIcon(getClass().getResource("images/b_help.png"));
		b_retu1.img_b1 = b_retu1.img_now =  new ImageIcon(getClass().getResource("images/b_retu1.png"));
		b_retu2.img_b1 = b_retu2.img_now =  new ImageIcon(getClass().getResource("images/b_retu2.png"));
		b_stop.img_b1 = b_stop.img_now =  new ImageIcon(getClass().getResource("images/b_stop.png"));
		
		//高亮
		b_easy.img_b2 = new ImageIcon(getClass().getResource("images/b_easy_2.png"));
		b_diff.img_b2 = new ImageIcon(getClass().getResource("images/b_diff_2.png"));
		b_help.img_b2 = new ImageIcon(getClass().getResource("images/b_help_2.png"));
		b_retu1.img_b2 = new ImageIcon(getClass().getResource("images/b_retu1_2.png"));
		b_retu2.img_b2 = new ImageIcon(getClass().getResource("images/b_retu2_2.png"));
		b_stop.img_b2 = new ImageIcon(getClass().getResource("images/b_stop_2.png"));
		
		//实例化生命类
		life = new Life();
		
		for(int i = 0 ; i< 4 ; i++)
		{
			money[i] = new Money(56,56);
		}
		//实例化炸弹类对象
		for(int i = 0 ; i<4 ; i++)
		{
			bomb[i] = new Bomb(50,65);
		}
		//实例化炮弹对象
		for(int i = 0 ; i<4 ; i++)
		{
			shell[i] = new Shell();
		}
		
		//焦点控制
		this.setFocusable(true);
		//添加鼠标、鼠标移动、键盘监听
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//实例化计时器
		timer.schedule(new MyTask(),0, 20);
		
	}
	

	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		//绘制特殊图像光标
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(img_finger_now.getImage(), new Point(), null));
		
		if(state ==1)
		{
			//绘制主页面和三个按钮
			//g.drawImage(img_state1.getImage(), 0,0,900,600, null);
			g.drawImage(img_character_beginning.getImage(), 0,0,900,600, null);
			g.drawImage(img_money_beginning.getImage(), 0,0,900,600, null);
			
			b_easy.draw(g);
			b_diff.draw(g);
			b_help.draw(g);
				
		}
		
		
		else if(state ==2)
		{
			//绘制游戏页面
			g.drawImage(img_state2.getImage(),0,0,900,600, null);
			if(guid_count < 350)
			{
				g.drawImage(img_guid.getImage(),0,0,900,600, null);
			}
			//绘制角色
			character.draw(g);
			
			//绘制金币
			for(int i = 0 ; i<num ; i++)
			{
				money[i].draw(g);
			}
			//绘制炸弹
			for(int i = 0 ; i<num2 ; i++)
			{				
				bomb[i].draw(g);
			}	
			
			//条件绘制炮弹
			for(int i = 0 ; i< 4 ; i++)
			{
				shell[i].draw(g);
			}
			
			//绘制三个生命值
			life.draw(g);
			
			//绘制鸟
			bird.draw(g,character);
			
			//绘制返回按钮
			b_retu2.draw(g);
			b_stop.draw(g);
			
			//绘制得分
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("新蒂小丸子小学版",Font.BOLD,30));
			g.drawString("金币数：" + score, 50, 70);
			g.drawString("钻石数：" + bird.get_bird, 50, 130);
			
		}
		else if(state ==3)
		{
			//绘制帮助和返回按钮
			g.drawImage(img_state3.getImage(),0,0,900,600, null);
			b_retu1.draw(g);
		}
		else if(state ==4)
		{
			//绘制失败和返回
			g.drawImage(img_state4.getImage(),0,0,900,600, null);
			b_retu1.draw(g);
		}
		else if(state ==5)
		{
			//绘制成功和返回
			
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
					//金币和炸弹总数初始化
					character.update();
					//金币位置更新，落地则循环
					for(int i  = 0 ; i<num ; i++)
					{
						money[i].update();
						
					}
					//金币位置更新，落地则循环
					for(int i = 0 ; i<num2 ; i++)
					{
						bomb[i].update();
					}
					
					//炮弹位置更新
					for(int i = 0 ; i<4 ; i++)
					{
						if(shell[i].get_fire == true)
						{
							shell[i].update();
						}
					}
					//
					bird.update();
					
					//更新得分，使用得分方法
					get_score();
				}
				repaint();
				
			}
		}	
	}
	//统计得分、修改生命值
	void get_score()
	{
		//对所有金币统计得分
		for(int i = 0; i <num ; i++)	
		{
			score = money[i].get_score_money(character,score);
			
		}
		//对所有炸弹扣生命值的统计
		for(int i = 0; i<num2 ; i++)	
		{
			life = bomb[i].get_score_bomb(character,life);
			
		}
		//是否击中鸟
		for(int i = 0 ; i< 4 ; i++)
		{
			bird.get_fired(shell[i]);
		}
		//捡鸟
		score = bird.picked(character, score);
		//是否死亡
		state = life.whether_die(character);
		//得分达到上限，获胜
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
	//简单模式开始
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
	//困难模式开始
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
	//所有数值初始化
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
			//键盘左键，角色左移
			if( e.getKeyCode() == KeyEvent.VK_LEFT )
			{
				
				
				if(character.get_boom == false)
				{
					//赛道距离90
					character.img_now = character.img_left;
					if(character.x>=30)
					{
						character.speed = -10;
					}
				}
				
				
			}
			//键盘右键，角色右移
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
			//键盘空格键，发射炮弹.点火判断为true,炮弹下标数从1开始，到4则循环
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
			//键盘左键松开，角色停下
			if( character.get_boom == false && e.getKeyCode() == KeyEvent.VK_LEFT)
			{

				//赛道距离90
				character.img_now = character.img_left;
				character.speed = 0;
			}
			//键盘右键松开，角色停下
			else if( character.get_boom == false && e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				character.img_now = character.img_right;
				character.speed = 0;
				
			}
		}
		
	}
	
	
	public void mouseClicked(MouseEvent e) {

	}

//鼠标监听
	public void mousePressed(MouseEvent e) 
	{
		img_finger_now = img_finger_2;
		//主页面，简单、困难、帮助三个按钮，点击后按钮点击判断归false
		if(state == 1)
		{
			//简单按钮，进入游戏见面，金币、炸弹个数初始化，加速度初始化
			if(b_easy.touch(e.getX(), e.getY())==true)
			{
				easy_beginning();
			}
			//困难按钮，进入游戏界面，金币、炸弹个数增加，加速度增加
			else if(b_diff.touch(e.getX(), e.getY())==true)
			{
				diff_beginning();
			}
			//帮助按钮，跳转到帮助界面
			else if(b_help.touch(e.getX(), e.getY())==true)
			{
				state = 3;
				b_help.result = false;
			}
		}
		
		//游戏进行的返回按钮，跳转主页面，按钮点击判断归false,生命值、鸟和炮弹的判断值初始化
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
		
		//帮助的返回按钮，跳转主页面，按钮点击判断归false
		else if(state == 3)
		{
			if(b_retu1.touch(e.getX(), e.getY())==true)
			{
				state = 1;
				b_retu1.result = false;
				
			}
			
		}
		//失败的返回按钮的处理，跳转主页面，生命、得分、鸟、炮弹初始化，按钮点击归判断归false
		else if(state == 4)
		{
			if(b_retu1.touch(e.getX(), e.getY())==true)
			{
				state = 1;
				b_retu1.result = false;
				everything_init();
			}
			
			
		}
		//成功的返回按钮的处理，跳转主页面，得分归零，按钮点击判断归false
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
		//鼠标移动，图片变换高亮
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