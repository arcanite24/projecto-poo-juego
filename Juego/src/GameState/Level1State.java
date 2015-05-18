package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.TileMap;

public class Level1State extends GameState {
	
	private TileMap tilemap;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		tilemap = new TileMap(30);
		tilemap.loadTiles("/Tilesets/t.gif");
		tilemap.loadMap("/Maps/map_test.map");
		tilemap.setPosition(0, 0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT); 
		tilemap.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
