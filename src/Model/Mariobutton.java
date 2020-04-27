package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class Mariobutton extends Button{
	private final String FONT_PATH = "src/model/resourses/SuperMario256.ttf";
	private final String RELEASED = "-fx-background-color: transparent; -fx-background-image: url('/model/resourses/yellow_button00.png');" ;
	private final String PRESED  = "-fx-background-color: transparent; -fx-background-image: url('/model/resourses/yellow_button01.png');";

	public Mariobutton(String text)
	{
		setText(text);
		setbuttonfont();
		setPrefHeight(40);
		setPrefWidth(190);
		setbuttonreleasedstyle();
		listeners();
	}
	
	
	private  void setbuttonfont()
	{
		
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("verdana",23));
		}
	}
	
	
	public void setbuttonpressedstyle()
	{
		setStyle(PRESED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	
	
	public void setbuttonreleasedstyle()
	{
		setStyle(RELEASED);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
		
	}
	
	
	private void listeners()
	{
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
					setbuttonpressedstyle();
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY))
					setbuttonreleasedstyle();
				
			}
		});
		
		setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setEffect(new DropShadow());
				
			}
		});
		
		setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setEffect(null);
				
			}
		});
	}
	
}
