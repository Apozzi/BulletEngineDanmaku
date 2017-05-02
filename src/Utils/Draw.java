package Utils;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import Game.Render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Draw {
	
	static IntBuffer buffer;
	
	
	static {
	    buffer = BufferUtils.createIntBuffer(1);
	    GL15.glGenBuffers(buffer);
	}
	
	public static void drawRect(float x, float y, float width, float height, float rot)
	   {
		  GL11.glDisable(GL11.GL_TEXTURE_2D);
		  GL11.glLoadIdentity();
	      {
	         glTranslatef(x, y, 0); // Shifts the position
	         glRotatef(rot, 0, 0, 1);

	         glBegin(GL_QUADS);
	         {
	            glVertex2f(0, 0);
	            glVertex2f(0, height);
	            glVertex2f(width, height);
	            glVertex2f(width, 0);
	            
	         }
	         glEnd();
	      }
	      GL11.glEnable(GL11.GL_TEXTURE_2D);
	   }
	
	public static void drawRectVBO()
	   {
		

	   }
	
	public static void drawImage(int textureID, float x, float y, float width, float height, float rot)
	   {
		GL11.glLoadIdentity();
	      {
	         glTranslatef(x, y, 0); // Shifts the position
	         glRotatef(rot, 0, 0, 1);

	         GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	 		 GL11.glBegin(GL11.GL_QUADS);
	 		 GL11.glTexCoord2f(1, 1);
	 	     glVertex2f(width, 0);
	 	     
	 	     GL11.glTexCoord2f(1, 0);
	 	     glVertex2f(width, height);
	 	     
	 	     GL11.glTexCoord2f(0, 0);
	 	     glVertex2f(0, height);
	 		 
	 	     GL11.glTexCoord2f(0, 1);
	 	     glVertex2f(0, 0);

	 	     GL11.glEnd();
	      }
	    
	   }
	
	public static void drawImageCenteredRotation(int textureID, float x, float y, float width, float height, float rot) {
		GL11.glLoadIdentity();
		glTranslatef(x, y, 0);
		
		glTranslatef((width/2), (height/2), 0);
		glRotatef(rot, 0, 0, 1);
		glTranslatef(-(width/2), -(height/2), 0);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(1, 1);
	    glVertex2f(width, 0);
	     
	    GL11.glTexCoord2f(1, 0);
	    glVertex2f(width, height);
	     
	    GL11.glTexCoord2f(0, 0);
	    glVertex2f(0, height);
		 
	    GL11.glTexCoord2f(0, 1);
	    glVertex2f(0, 0);
	

	    GL11.glEnd();
	
	}

	public static void drawCircle(float x, float y, float radius) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glLoadIdentity();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(radius, radius, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= 50; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
			double angle = Math.PI * 2 * i / 50;
			GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	

}
