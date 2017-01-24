package com.game.src.main;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.game.src.main.controllers.RenderController;

public class FrameRender implements Runnable
{
	private boolean running;
	private Graphics _graphics;
	private BufferStrategy _bufferStartegy;
	private RenderController _renderController;
	private Thread _thread;
	private BufferedImage _backGround = null;
	private ImageLoader _loader;
	private long _FPS = 60;

	public FrameRender(BufferStrategy bufferStrat, RenderController ctrl) {
		_bufferStartegy = bufferStrat;
		_renderController = ctrl;
		init();
	}

	public void run() {
		long curTime;
		long frameRenderTime;
		xfrm = AffineTransform.getScaleInstance(1.0, 1.0);

		while(running) {
			frameRenderTime = System.currentTimeMillis();
			render();
			curTime = System.currentTimeMillis();
			onEnterFrame(curTime, frameRenderTime);
		}
	}

	private Graphics2D canvas;
	private BufferedImage image;
	private AffineTransform xfrm;
	private AffineTransform paintXfrm;

	private void render() {
		_graphics = _bufferStartegy.getDrawGraphics();

		//i think i have to place this on every image.
		Graphics2D g2d = (Graphics2D) _graphics;
		paintXfrm = g2d.getTransform();
//		g2d.scale(1, -1);
		try {
			paintXfrm.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		g2d.translate(320, -240);
		g2d.transform(xfrm);
		//left off here
		//g2d.translate(image.getWidth() * -0.5, image.getHeight() * -0.5);


		_graphics.drawImage(_backGround, 0, 0, null);
		_renderController.render(_graphics);
		
		_graphics.dispose();
		_bufferStartegy.show();

		//restore
		g2d.setTransform(paintXfrm);
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
		_bufferStartegy = null;
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
