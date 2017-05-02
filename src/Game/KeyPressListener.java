package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class KeyPressListener extends GLFWKeyCallback{

	public static boolean[] keys = new boolean[65536];
	
	
	// The GLFWKeyCallback class is an abstract method that
	// can't be instantiated by itself and must instead be extended
	// 
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		keys[key] = action != GLFW_RELEASE;
	}
	
	// boolean method that returns true if a given key
	// is pressed.
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

	
}
