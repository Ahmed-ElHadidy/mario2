package view;

import java.util.ArrayList;
import java.util.HashMap;

import chrachter.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class gameManger {
	
	private static final String[] level1= {
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000000000000000000000000000000",
			"000111000000000000000000000000",
			"000000001110000000000000000000",
			"000000000000011100000000000000",
			"000001110000000000011100001100",
			"111111110011110001111110011111"
			};
	
	private int levelwidth;
	private HashMap <KeyCode,Boolean> Keys = new HashMap<KeyCode,Boolean>();
	private ArrayList<Node> platforms= new ArrayList<Node>();
	private AnchorPane gamepane = new AnchorPane();
	private Player player ;
	
	
	public gameManger(Scene mainscene,AnchorPane mainpane) {
		initliazecont(mainpane);
		mainscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Keys.put(event.getCode(),true);
				
			}
		});
		
		mainscene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Keys.put(event.getCode(),false);
				player.animation.stop();
				player.setplayerimageViewport();
			}
		});
		
		AnimationTimer timer =new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				player.update(levelwidth, platforms, Keys);
				if(isover()) {
					this.stop();
				}
			}
			
		};
		timer.start();
		
	}
	
	private void initliazecont(AnchorPane mainpane) {
		levelwidth = level1[0].length()*60;
		for (int i =0;i<level1.length;i++) {
			String line = level1[i];
			for(int j=0;j<line.length();j++)
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					Node platform = createentity(j*60,i*60,60,60);
					platforms.add(platform);
					break;
				}
		}
		
		player = new Player();
		player.createplayer(10, 300, gamepane);
		player.getplayerimage().translateXProperty().addListener((obs,old,newvalue)->{
			int offset = newvalue.intValue();
			if(offset>384 && offset<levelwidth-384)
			{
				gamepane.setLayoutX(-(offset-384));
			}
		});
		
		mainpane.getChildren().add(gamepane);
	}
		
		public Node createentity(int x, int y, int width, int height) {
			ImageView entity = new ImageView(new Image("src/view/resourses/wall.png",60,60,false,true));
			entity.setTranslateX(x);
			entity.setTranslateY(y);
			gamepane.getChildren().add(entity);
			return entity;
		}
		
		public boolean isover() {
			if(player.getplayerimage().getTranslateY()<1024)
				return false;
			else
				return true;
		}

}


