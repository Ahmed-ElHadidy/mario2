package chrachter;


import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane implements character  {
	private boolean canjump= true;
	private Point2D playerVelocity = new Point2D(0,0);
	private ImageView playerimage ;
	public SpriteAnimation animation;
	
	int count = 3;
    int columns = 3;
    int offsetX = 96;
    double offsetY = 32.5;
    int width = 16;
    int height = 16;
	
		
	@Override
	public void update(int levelwidth,ArrayList<Node> platforms,HashMap<KeyCode, Boolean> Keys) {
		
		if(isPressed(KeyCode.W,Keys) && playerimage.getTranslateY()>=5) {
			jumpplayer();
		}
		if(isPressed(KeyCode.A,Keys)&&playerimage.getTranslateX()>=5) {
			moveX(-5,platforms);
			//setScaleX(-1);
			animation.play();
			
		}
		if(isPressed(KeyCode.D,Keys)&&playerimage.getTranslateX()+30<levelwidth-5) {
			moveX(5,platforms);
			//setScaleX(1);
			animation.play();
		}
		if(playerVelocity.getY()<10)
			playerVelocity= playerVelocity.add(0,1);
			
		moveY((int)playerVelocity.getY(),platforms);
		
	}

	@Override
	public void moveX(int value ,ArrayList<Node> platforms) {
		boolean moveRight = value>0;
		for(int i =0;i<Math.abs(value);i++) {
			for(Node platform:platforms) {
				if(playerimage.getBoundsInParent().intersects(platform.getBoundsInParent()))
					if(moveRight) {
						if(playerimage.getTranslateX()+30 == platform.getTranslateX())
							return;
					}
					else {
						if(playerimage.getTranslateX() == platform.getTranslateX()+60)
							return;
					}
			}
			
			playerimage.setTranslateX(playerimage.getTranslateX()+ (moveRight?1:-1));
		}		
		
		
	}

	public void createplayer(int x, int y,AnchorPane pane) {
		playerimage = new ImageView(new Image("src/chrachter/mario (1).png"));
		playerimage.setTranslateX(x);
		playerimage.setTranslateY(y);
		playerimage.setFitWidth(30);
		playerimage.setFitHeight(30);
		playerimage.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(playerimage,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
		pane.getChildren().add(playerimage);
		
	}
	
	public void moveY(int value,ArrayList<Node> platforms) {
		boolean movedown = value>0;
		for(int i=0;i<Math.abs(value);i++) {
			for(Node platform:platforms) {
				if(playerimage.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if(movedown) {
						if(playerimage.getTranslateY()+30== platform.getTranslateY()) {
							playerimage.setTranslateY(playerimage.getTranslateY()-1);
							canjump =true;
							return;
						}
					}
					else {
						if(playerimage.getTranslateY() == platform.getTranslateY()+60) {
							return;
						}
					}
				}
			}
			playerimage.setTranslateY(playerimage.getTranslateY()+(movedown?1:-1));
		}
	}
	
	private boolean isPressed(KeyCode key,HashMap<KeyCode,Boolean> Keys) {
		return Keys.getOrDefault(key, false);
	}
	
	
	private void jumpplayer() {
		if(canjump) {
			playerVelocity =playerVelocity.add(0,-30);
			canjump =false;
		}
	}
	public Node getplayerimage() {
		return playerimage;
	}
	
	public void setplayerimageViewport() {
		playerimage.setViewport(new Rectangle2D(offsetX-16,offsetY,width,height));
	}

}

