package Model;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class MarioSubScene extends SubScene {
	
	private final static String FONT_PATH = "src/Model/resourses/SuperMario256.ttf";
	private final static String BACKGROUND_PATH ="src/Model/resourses/yellow_panel.png";
	
	private boolean ishidden ;

	public MarioSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_PATH,600,400,false,true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		setLayoutX(1100);
		setLayoutY(200);
		ishidden = true;
		
		
	}
	
	public void movesubscene()
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		if(ishidden) {
		transition.setToX(-750);
		ishidden= false;
		}
		else {
			transition.setToX(0);
			ishidden = true;
		}
		transition.play();
		
	}

}
