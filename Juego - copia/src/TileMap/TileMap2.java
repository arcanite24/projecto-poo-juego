package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap2 {
	
	private double x;
	private double y;
	
	private double xmax;
	private double xmin;
	private double ymax;
	private double ymin;
	private double tween;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int ancho;
	private int alto;
	
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	private int rowOffset;
	private int colOffset;
	private int rowsToDraw;
	private int colsToDraw;
	
	public TileMap2(int tilesize) {
		this.tileSize = tilesize;
		rowsToDraw = GamePanel.HEIGHT / tileSize;
		colsToDraw = GamePanel.WIDTH / tileSize;
		tween = 0.07;
	}
	
	public void loadTiles(String s) {
		
		try {
			
			tileset  = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for(int col=0; col<numTilesAcross; col++) {
				subimage = tileset.getSubimage(col*tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			
			ancho = numCols * tileSize;
			alto = numRows * tileSize;
			
			xmin = GamePanel.WIDTH - ancho;
			xmax = 0;
			ymin = GamePanel.HEIGHT - alto;
			ymax = 0;
			
			String delims = "\\s+";
			for(int rows=0; rows < numRows; rows++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col=0; col < numCols; col++) {
					map[rows][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTileSize() { return tileSize; }
	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
	public int getWidth() { return ancho; }
	public int getHeight() { return alto; }
	public void setTween(double d) { tween = d; }
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y) {
		
		this.x = (x - this.x) * tween;
		this.y = (x - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g) {
		
		for(int row=rowOffset; row < rowOffset + rowsToDraw; row++) {
			
			if(row >= numRows) break;
			
			for(int col=colOffset; col < colOffset + colsToDraw; col++) {
				
				if(col >= numCols) break;
				
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize,(int)y + row * tileSize, null);
				
			}
		}
		
	}
	
	

}
