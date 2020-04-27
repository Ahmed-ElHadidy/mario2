package chrachter;


import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class Player implements character  {
	private boolean canjump= true;
	private Point2D playerVelocity = new Point2D(0,0);
	private ImageView playerimage ;
	
		
	@Override
	public void update(int levelwidth,ArrayList<Node> platforms,HashMap<KeyCode, Boolean> Keys) {
		
		if(isPressed(KeyCode.W,Keys) && playerimage.getTranslateY()>=5) {
			jumpplayer();
		}
		if(isPressed(KeyCode.A,Keys)&&playerimage.getTranslateX()>=5)
			moveX(-5,platforms);
		if(isPressed(KeyCode.D,Keys)&&playerimage.getTranslateX()+16<levelwidth-5)
			moveX(5,platforms);
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
						if(playerimage.getTranslateX()+16 == platform.getTranslateX())
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

	public  Node createplayer(int x, int y, int width, int height,AnchorPane pane) {
		playerimage = new ImageView(new Image("src/chrachter/mario1.png"));
		playerimage.setTranslateX(x);
		playerimage.setTranslateY(y);
		pane.getChildren().add(playerimage);
		
		return playerimage;
	}
	
	public void moveY(int value,ArrayList<Node> platforms) {
		boolean movedown = value>0;
		for(int i=0;i<Math.abs(value);i++) {
			for(Node platform:platforms) {
				if(playerimage.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if(movedown) {
						if(playerimage.getTranslateY()+32== platform.getTranslateY()) {
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

}

