package com.game.src.main;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.game.src.main.controllers.RenderController;

public class FrameRender implements Runnable
{
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private long _FPS = 60;
	private Thread _thread;
	private boolean running;
	private Graphics _graphics;
	private ImageLoader _loader;
	private BufferStrategy _bufferStrategy;
	private BufferedImage _backGround = null;
	private RenderController _renderController;

	public FrameRender(BufferStrategy bufferStrategy, int height, int width) {
		WIDTH = width;
		HEIGHT = height;
		_bufferStrategy = bufferStrategy;
		_renderController = new RenderController();
		init();
	}

	public void run() {
		long curTime;
		long frameRenderTime;

		while(running) {
			frameRenderTime = System.currentTimeMillis();
			render();
			curTime = System.currentTimeMillis();
			onEnterFrame(curTime, frameRenderTime);
		}
	}
	
	private void render() {
		_graphics = _bufferStrategy.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) _graphics;
		AffineTransform at = g2d.getTransform();
		int widthCenter = WIDTH / 2;
		int heightCenter = HEIGHT / 2;
		g2d.translate(widthCenter, heightCenter);
		g2d.scale(1, -1);

		g2d.drawImage(_backGround, 0, 0, null);
		_renderController.render(g2d);

		//Draw Circle
		g2d.setColor(Color.blue);
		Ellipse2D.Float e1 = new Ellipse2D.Float(-20,-20,40,40);
		g2d.fill(e1);

		g2d.dispose();
		_bufferStrategy.show();
		g2d.setTransform(at);
	}

	private void init() {
		_loader = new ImageLoader();
		try {
			_backGround = _loader.loadImage("/backGround.png");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void onEnterFrame(long curTime, long frameRenderTime) {
		long totalRenderTime = curTime - frameRenderTime;
		long _aTick = (1000 / _FPS);
		if(_aTick - totalRenderTime > 0) {
			javaDelay(_aTick - totalRenderTime);
		}
	}

	@SuppressWarnings("static-access")
	private void javaDelay(long _delay) {
		try {
			_thread.sleep(_delay);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void startThread() {
		_thread = new Thread(this);
		_thread.setName("render");
		running = true;
		_thread.start();
	}
	
	public synchronized void stopThread() {
		_graphics.dispose();
		_graphics = null;
		_bufferStrategy = null;
		_renderController = null;
		_backGround = null;
		_loader = null;		
		
		try {
			running = false;
			_thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

}
