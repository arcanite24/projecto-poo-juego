package Entity;

import java.awt.Rectangle;

import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;

public abstract class MapObject {
	
	protected TileMap tilemap;
	protected int tilesize;
	protected double xmap;
	protected double ymap;
	
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	protected int ancho;
	protected int alto;
	
	protected int colancho;
	protected int colalto;
	
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected int xtemp;
	protected int ytemp;
	
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean botLeft;
	protected boolean botRight;
	
	protected Animation animation;
	protected int preAction;
	protected int currAction;
	protected boolean facingRight;
	
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	protected double moveSpeed;
	protected double maxSpeed;
	protected double gravity;
	protected double stopSpeed;
	protected double maxGravity;
	protected double maxJump;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	public MapObject(TileMap tm) {
		tilemap = tm;
		tilesize = tm.getTileSize();
	}
	
	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - colancho, (int)y - colalto, colancho, colalto);
	}
	
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int)(x - colancho / 2) / tilesize;
		int rightTile = (int)(x + colancho / 2 - 1) / tilesize;
		int topTile = (int)(y - colalto / 2) / tilesize;
		int botTile = (int)(y + colalto / 2 - 1) / tilesize;
		
		int tl = tilemap.getType(topTile, leftTile);
		int tr = tilemap.getType(topTile, rightTile);
		int bl = tilemap.getType(botTile, leftTile);
		int br = tilemap.getType(botTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		botLeft = bl == Tile.BLOCKED;
		botRight= br == Tile.BLOCKED;
				
		
	}
	
	public void checkTilemapCollision() {
		
		currCol = (int)x / tilesize;
		currRow = (int)y / tilesize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		calculateCorners(x, ydest);
		if(dy < 0) {
			if(topLeft || topRight) {
				ytemp = currRow * tilesize + colalto / 2;
			}
		}
		if(dy > 0) {
			if(botLeft || botRight) {
				ytemp = (currRow + 1) * tilesize - colalto / 2;
			} else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if(dx < 0) {
			if(topLeft || botLeft) {
				dx = 0;
				xtemp = currCol * tilesize + colancho / 2;
			} else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight || botRight) {
				dx = 0;
				xtemp = (currCol + 1) * tilesize - colancho / 2;
			}
		}
		
		if(!falling) {
			calculateCorners(x, ydest + 1);
			if(!botLeft && !botRight) {
				falling = true;
			}
		}
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getColancho() {
		return colancho;
	}

	public void setColancho(int colancho) {
		this.colancho = colancho;
	}

	public int getColalto() {
		return colalto;
	}

	public void setColalto(int colalto) {
		this.colalto = colalto;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = tilemap.getX();
		ymap = tilemap.getY();
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean notOnScreen() {
		return x + xmap < 0 || x + xmap - ancho > GamePanel.WIDTH || y + ymap + alto <0 || y + ymap - alto > GamePanel.HEIGHT;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	
	
}

