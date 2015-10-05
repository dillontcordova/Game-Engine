package com.game.src.main;
import java.awt.Graphics;
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

		while(running) {
			frameRenderTime = System.currentTimeMillis();
			render();
			curTime = System.currentTimeMillis();
			onEnterFrame(curTime, frameRenderTime);
		}
	}
	
	private void render() {
		_graphics = _bufferStartegy.getDrawGraphics();
		
		_graphics.drawImage(_backGround, 0, 0, null);
		_renderController.render(_graphics);
		
		_graphics.dispose();
		_bufferStartegy.show();
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
