package chrachter;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public interface character {
	 public abstract void update(int levelwidth,ArrayList<Node>platforms,HashMap<KeyCode, Boolean> keys) ;
	 public abstract void moveX(int value,ArrayList<Node> platform);
}
