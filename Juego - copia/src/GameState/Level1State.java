package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.Player;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class Level1State extends GameState {
	
	private TileMap tilemap;
	private Player player;
	private Background bg1;
	private Background bg2;
	
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		bg1 = new Background("/Backgrounds/bg.png", 1);
		bg1.setVector(0, 0);
		bg2 = new Background("/Backgrounds/bg2.png", 1);
		bg2.setVector(0, 0);
		init();
	}

	@Override
	public void init() {
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/t.gif");
		tilemap.loadMap("/Maps/map30.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);
		
		player = new Player(tilemap);
		player.setPosition(200,200);
	}

	public void update() {
		
		player.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - player.getx() + 32,GamePanel.HEIGHT / 2 - player.getx());
		bg1.update();
		bg2.update();
		
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
		bg1.draw(g);
		bg2.draw(g);
		tilemap.draw(g);
		player.draw(g);
		
		
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setUp(true);
		if(k == KeyEvent.VK_S) player.setDown(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_SHIFT) player.setGliding(true);
		if(k == KeyEvent.VK_P) player.setScratching();
		if(k == KeyEvent.VK_O) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setUp(false);
		if(k == KeyEvent.VK_S) player.setDown(false);
		if(k == KeyEvent.VK_SPACE) player.setJumping(false);
		if(k == KeyEvent.VK_SHIFT) player.setGliding(false);
	}

}
