package view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Model.MarioSubScene;
import Model.Mariobutton;


public class viewmanger {

	private static final int WIDTH = 768;
	private static final int HEIGHT = 1024;
	private Stage mainstage;
	private Scene mainscene;
	private AnchorPane mainpane;
	List<Mariobutton> menubuttons;
	

	private final static int MENU_BUTTON_X = 100;
	private final static int MENU_BUTTON_Y = 150;
	private gameManger game;
	
	
	private MarioSubScene creditsubscene;
	private MarioSubScene helpsubscene;
	private MarioSubScene scoresubscene;
	
	private MarioSubScene scenetohide;
	private ImageView logo;
	private Background bg;
	
	public viewmanger()
	{
		menubuttons = new ArrayList<>();
		mainpane = new AnchorPane();
		mainscene = new Scene(mainpane,HEIGHT,WIDTH);
		mainstage = new Stage();
		mainstage.setScene(mainscene);
		createbutton();
		createsubscene();
		createbackgroundimage();
		createLogo();
		
	}
	
	
	private void createsubscene() {
		creditsubscene = new MarioSubScene();
		helpsubscene = new MarioSubScene();
		scoresubscene = new MarioSubScene();
		mainpane.getChildren().addAll(creditsubscene,helpsubscene,scoresubscene);
		
	}
	
	private void showsubscene(MarioSubScene subscene)
	{
		if(scenetohide != null)
		{
			scenetohide.movesubscene();
		}
			subscene.movesubscene();
			scenetohide = subscene;
	}


	public Stage getMainstage() {
		return mainstage;
	}
	
	
	private void addmenubutton(Mariobutton button)
	{
		button.setLayoutX(MENU_BUTTON_X-1000);
		button.setLayoutY(MENU_BUTTON_Y + menubuttons.size()*100);
		
		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(1), button);
		trans1.setDelay(Duration.seconds(1));
		trans1.setCycleCount(1);
		trans1.setToX(1000);
		trans1.play();
		
		menubuttons.add(button);
		mainpane.getChildren().add(button);
	}
	
	
	private void addStartbutton()
	{
		Mariobutton button = new Mariobutton("START");
		addmenubutton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				mainpane.getChildren().removeAll(creditsubscene,helpsubscene,scoresubscene,logo,menubuttons.get(0),
						menubuttons.get(1),menubuttons.get(2),menubuttons.get(3),menubuttons.get(4));
				BackgroundFill background =new BackgroundFill(Color.CYAN,null, null);
				Background back = new Background(background);
				mainpane.setBackground(back);
				
				game = new gameManger(mainscene,mainpane);
				
				
				
				
			}
		});
	}
	
	
	private void addscorebutton()
	{
		Mariobutton button = new Mariobutton("SCORES");
		addmenubutton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showsubscene(scoresubscene);
				
			}
		});
	}
	
	
	private void addhelpbutton()
	{
		Mariobutton button = new Mariobutton("HELP");
		addmenubutton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showsubscene(helpsubscene);
				
			}
		});
	}
	
	
	private void addcreditsbutton()
	{
		Mariobutton button = new Mariobutton("CREDITS");
		addmenubutton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showsubscene(creditsubscene);
				
			}
		});
	}
	
	
	private void addexitbutton()
	{
		Mariobutton button = new Mariobutton("EXIT");
		addmenubutton(button);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainstage.close();
				
			}
		});
	}
	
	
	private void createbutton()
	{	
		addStartbutton();
		addscorebutton();
		addhelpbutton();
		addcreditsbutton();
		addexitbutton();
	}
	
	private void createbackgroundimage()
	{
		Image backgroundimage = new Image("view/resourses/blue.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundimage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		bg =new Background(background);
		mainpane.setBackground(bg);
	}
	
	public void createLogo()
	{
		logo = new ImageView("/view/resourses/741eaa9c45587eb0cb0a7c209b39da72.png");
		logo.setLayoutX(400);
		logo.setLayoutY(-50);
		
		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(1), logo);
		trans1.setToY(100);
		trans1.setCycleCount(1);
		trans1.play();
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
				
			}
		});
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
		});
		
		mainpane.getChildren().add(logo);
	}
	
}
