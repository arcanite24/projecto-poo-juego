package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.Player;
import Main.GamePanel;
import TileMap.TileMap;

public class Level1State extends GameState {
	
	private TileMap tilemap;
	private Player player;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		tilemap = new TileMap(32);
		tilemap.loadTiles("/Tilesets/tiles.png");
		tilemap.loadMap("/Maps/map.map");
		tilemap.setPosition(0, 0);
		
		player = new Player(tilemap);
		player.setPosition(200,200);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		player.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - player.getx(),GamePanel.HEIGHT / 2 - player.getx());
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT); 
		tilemap.draw(g);
		player.draw(g);
		
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}

}
