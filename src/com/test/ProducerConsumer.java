package com.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ProducerConsumer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Object> myList = new LinkedList<Object>();
	private int MAX = 4;
	private int count = 0;
	private final Lock lock = new ReentrantLock();
	private final Condition full = lock.newCondition();
	private final Condition empty = lock.newCondition();
	final BufferedImage[] im = new BufferedImage[3];
	JProgressBar p1 = new JProgressBar();
	JProgressBar p2 = new JProgressBar();
	
	Producer producer;//生产者和消费者只能各有一个对象，不然无法中断
	Consumer consumer;
	ImgArea imgArea=new ImgArea();
	public ProducerConsumer() {
		JButton b2 = new JButton("开始生产");
		JButton b3 = new JButton("ͣ停止生产");
		JButton b4 = new JButton("开始消费");
		JButton b5 = new JButton("ͣ停止消费");
		imgArea.setBounds(0, 125, 620, 175);
		
		b2.setBounds(50, 100, 80, 80);
		b2.setSize(100, 30);
		b3.setBounds(170, 100, 80, 80);
		b3.setSize(100, 30);
		b4.setBounds(500, 400, 80, 80);
		b4.setSize(100, 30);
		b5.setBounds(620, 400, 80, 80);
		b5.setSize(100, 30);
		

		p1.setOrientation(JProgressBar.HORIZONTAL);
		p1.setMinimum(0);
		p1.setMaximum(100);
		p1.setValue(0);
		p1.setStringPainted(true);
		p1.setBackground(Color.white);
		p1.setString("生产者进程");
		p1.setBounds(50, 10, 80, 80);
		p1.setSize(300, 60);

		p2.setOrientation(JProgressBar.HORIZONTAL);
		p2.setMinimum(0);
		p2.setMaximum(100);
		p2.setValue(0);
		p2.setStringPainted(true);
		p2.setBackground(Color.white);
		p2.setString("消费者进程");
		p2.setBounds(420, 300, 80, 80);
		p2.setSize(300, 60);

		this.setSize(800, 500);
		this.setLayout(null);
		this.add(p1);
		this.add(p2);
		this.add(imgArea);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.show();
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new Producer().start();//生产者和消费者只能各有一个对象，不然无法中断，不能新建生产者对象了
				producer = new Producer();
				producer.flag=true;
				System.out.println("开始生产");
				producer.start();

			}
		});
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Producer().interrupt();//同理
				System.out.println("停止生产");
				producer.flag=false;
//				producer.interrupt();
			}
		});
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//生产者和消费者只能各有一个对象，不然无法中断，不能新建消费者对象了
//				new Consumer().start();
				consumer = new Consumer();
				consumer.flag=true;
				System.out.println("开始消费");
				consumer.start();
			}
		});
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Consumer().interrupt();//同理
				consumer.flag=false;
				System.out.println("停止消费");
//				consumer.interrupt();
			}
		});
	}
	

	class Producer extends Thread {
		//为了控制线程的结束，用标志位方式，自带interrupt方法不怎么好用
		public  Boolean flag = true;
		
		
		public void run() {
			while (flag) {
				for (int i = 0; i < 110; i++) {
					p1.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.lock();// 获得锁
				try {
					while (myList.size() == MAX) {
						System.out.println("警告：已经满了!");
						full.await();// 实现互斥
					}
					Object o = new Object();
					if (myList.add(o) && count <= MAX) {
						System.out.println("生产者生产一个对象");
						count++;
						imgArea.repaint();//用这个绘制圆形
						Thread.sleep((long) (Math.random() * 3000));
						empty.signal();// 向消费者发送信号
					}
				} catch (InterruptedException ie) {
					System.out.println("生产者已经中断!");
				} finally {
					lock.unlock();// 释放锁
				}
			}
		}
	}

	class Consumer extends Thread {
		//为了控制线程的结束，用标志位方式，自带interrupt方法不怎么好用
		public Boolean flag = true;
		
		public void run() {
			while (flag) {
				for (int i = 0; i < 110; i++) {
					p2.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.lock();// 获得锁
				try {
					while (myList.size() == 0) {
						System.out.println("警告：产品池空了!");
						empty.await();// 实现互斥
					}
					Object o = myList.removeLast();
					System.out.println("消费者消费一个产品");
					count--;
//					paint(g);
					imgArea.repaint();
					Thread.sleep((long) (Math.random() * 3000));
					full.signal();// 向生产者发送信号
				} catch (InterruptedException ie) {
					System.out.println("消费者已经中断!");
				} finally {
					lock.unlock();// 释放锁
				}
			}
		}
	}
	
	class ImgArea extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			// 1.调用父类函数完成初始化
			  // 这句话不能少
			  super.paint(g);
////			  Image img = Toolkit.getDefaultToolkit().getImage("D://3.jpg");
////			  ImageInputStream imageInputStream=new FileImageInputStream("D://3.jpg");
//			  FileImageInputStream fileImageInputStream;
//			  BufferedImage img=null;//用图片总是报空指针，就用圆形代替吧
//			try {
//				fileImageInputStream = new FileImageInputStream(new File("D://3.jpg"));
//				img = ImageIO.read(fileImageInputStream);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			for (int i = 0; i < count; i++) {
				if (i==0) {
					  g.setColor(Color.red);
					  g.setFont(new Font("黑体", Font.BOLD, 15));
					  g.drawString("开始生产！", 10, 20);
				}
				 g.drawOval(30*i+10, 30, 30, 30);
				 if (count==MAX&&i==(MAX-1)) {
					  g.setColor(Color.red);
					  g.setFont(new Font("黑体", Font.BOLD, 15));
					  g.drawString("生产池已满！", 10, 100);
				}
			}
			
			if (count==0) {
				  g.setColor(Color.red);
				  g.setFont(new Font("黑体", Font.BOLD, 15));
				  g.drawString("尚未生产或者已消费完，生产池为空！", 10, 20);
			}
			
		}
	}

	public static void main(String[] args) {
		ProducerConsumer s = new ProducerConsumer();
	}
}
