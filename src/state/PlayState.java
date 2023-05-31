package state;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import Transition.TransitionType;
import entity.Button;
import helper.TextSize;
import main.GamePanel;
import manager.ImageManager;
import manager.TransitionManager;

/**
 * PlayState class represents the "play area" of the game 
 * This is where the user sees the game being played
 * 
 * @author Vachia Thoj
 *
 */
public class PlayState extends State
{
	//To manage images
	private ImageManager imageManager;
	
	//To manage transitions
	private TransitionManager transitionManager;
	
	//Strings to be drawn
	private String title;
	private String author;
	private String version;
	
	//Buttons
	private Button slideLeftButton;
	private Button slideRightButton;
	private Button slideUpButton;
	private Button slideDownButton;
	private Button horizontalSplitButton;
	private Button verticalSplitButton;
	private Button fadeToBlackButton;
	private Button fadeToWhiteButton;
	
	//Constructor
	public PlayState()
	{
		imageManager = ImageManager.instance();
		transitionManager = new TransitionManager(GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//Strings
		title = "TRANSITION DEMO";
		author = "Vachia Thoj (Vise)";
		version = "Ver. 1.0";
		
		//Create Buttons
		createButtons();
	}
	
	/**
	 * Method that creates/initializes Buttons
	 */
	private void createButtons()
	{
		BufferedImage [] buttonImages = imageManager.getButtonImages();
		
		slideLeftButton = new Button(buttonImages[0], buttonImages[1]);
		slideLeftButton.setX(GamePanel.WIDTH / 4);
		slideLeftButton.setY(GamePanel.HEIGHT / 4);
		
		slideRightButton = new Button(buttonImages[2], buttonImages[3]);
		slideRightButton.setX(GamePanel.WIDTH / 4);
		slideRightButton.setY(slideLeftButton.getY() + slideLeftButton.getHeight() + 32);
		
		slideUpButton = new Button(buttonImages[4], buttonImages[5]);
		slideUpButton.setX(GamePanel.WIDTH / 4);
		slideUpButton.setY(slideRightButton.getY() + slideRightButton.getHeight() + 32);
		
		slideDownButton = new Button(buttonImages[6], buttonImages[7]);
		slideDownButton.setX(GamePanel.WIDTH / 4);
		slideDownButton.setY(slideUpButton.getY() + slideUpButton.getHeight() + 32);
		
		horizontalSplitButton = new Button(buttonImages[8], buttonImages[9]);
		horizontalSplitButton.setX(GamePanel.WIDTH - (GamePanel.WIDTH / 4) - horizontalSplitButton.getWidth());
		horizontalSplitButton.setY(GamePanel.HEIGHT / 4);
		
		verticalSplitButton = new Button(buttonImages[10], buttonImages[11]);
		verticalSplitButton.setX(GamePanel.WIDTH - (GamePanel.WIDTH / 4) - verticalSplitButton.getWidth());
		verticalSplitButton.setY(horizontalSplitButton.getY() + horizontalSplitButton.getHeight() + 32);
		
		fadeToBlackButton = new Button(buttonImages[12], buttonImages[13]);
		fadeToBlackButton.setX(GamePanel.WIDTH - (GamePanel.WIDTH / 4) - fadeToBlackButton.getWidth());
		fadeToBlackButton.setY(verticalSplitButton.getY() + verticalSplitButton.getHeight() + 32);
		
		fadeToWhiteButton = new Button(buttonImages[14], buttonImages[15]);
		fadeToWhiteButton.setX(GamePanel.WIDTH - (GamePanel.WIDTH / 4) - fadeToWhiteButton.getWidth());
		fadeToWhiteButton.setY(fadeToBlackButton.getY() + fadeToBlackButton.getHeight() + 32);
	}
	
	private void startTransition(TransitionType transitionType)
	{
		slideLeftButton.setDisabled(true);
		slideRightButton.setDisabled(true);
		slideUpButton.setDisabled(true);
		slideDownButton.setDisabled(true);
		horizontalSplitButton.setDisabled(true);
		verticalSplitButton.setDisabled(true);
		fadeToBlackButton.setDisabled(true);
		fadeToWhiteButton.setDisabled(true);
		
		transitionManager.setTransition(transitionType);
		transitionManager.startTransition();
	}
	
	/**
	 * Method that updates Transition
	 */
	private void updateTransition()
	{
		//Update transition if transition is running
		if(transitionManager.isRunning())
		{
			transitionManager.update();
		}
	}
	
	/**
	 * Method that updates Buttons
	 */
	private void updateButtons()
	{
		//Update Buttons
		slideLeftButton.update();
		slideRightButton.update();
		slideUpButton.update();
		slideDownButton.update();
		horizontalSplitButton.update();
		verticalSplitButton.update();
		fadeToBlackButton.update();
		fadeToWhiteButton.update();
	}
	
	/**
	 * Method that performs an action when a Button has been clicked on
	 */
	private void buttonActions()
	{
		if(slideLeftButton.isMouseClickingButton())
		{
			slideLeftButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.SLIDE_LEFT);
		}
		else if(slideRightButton.isMouseClickingButton())
		{
			slideRightButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.SLIDE_RIGHT);
		}
		else if(slideUpButton.isMouseClickingButton())
		{
			slideUpButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.SLIDE_UP);
		}
		else if(slideDownButton.isMouseClickingButton())
		{
			slideDownButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.SLIDE_DOWN);
		}
		else if(horizontalSplitButton.isMouseClickingButton())
		{
			horizontalSplitButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.HORIZONTAL_SPLIT);
		}
		else if(verticalSplitButton.isMouseClickingButton())
		{
			verticalSplitButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.VERTICAL_SPLIT);
		}
		else if(fadeToBlackButton.isMouseClickingButton())
		{
			fadeToBlackButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.FADE_TO_BLACK);
		}
		else if(fadeToWhiteButton.isMouseClickingButton())
		{
			fadeToWhiteButton.setMouseClickingButton(false);
			
			startTransition(TransitionType.FADE_TO_WHITE);
		}
	}
	
	/**
	 * Method that updates PlayState
	 */
	public void update()
	{
		//If the transition has completed
		if(transitionManager.isDone())
		{
			//Enable Buttons
			slideLeftButton.setDisabled(false);
			slideRightButton.setDisabled(false);
			slideUpButton.setDisabled(false);
			slideDownButton.setDisabled(false);
			horizontalSplitButton.setDisabled(false);
			verticalSplitButton.setDisabled(false);
			fadeToBlackButton.setDisabled(false);
			fadeToWhiteButton.setDisabled(false);
		}
		
		//Update transition if transition is running
		updateTransition();
		
		//Update Buttons
		updateButtons();
		
		//Perform Button actions if necessary
		buttonActions();
	}
	
	/**
	 * Method that draws the background for PlayState
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawBackground(Graphics2D g)
	{
		//Draw Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	/**
	 * Method that draws the Strings/texts for the PlayState
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawStrings(Graphics2D g)
	{
		//Draw title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 32));
		int titleWidth = TextSize.getTextWidth(title, g);
		g.drawString(title, (GamePanel.WIDTH / 2) - (titleWidth / 2), 50);
				
		//Draw author
		g.setFont(new Font("Courier New", Font.BOLD, 16));
		g.drawString(author, 5, GamePanel.HEIGHT - 5);
				
		//Draw version
		int versionWidth = TextSize.getTextWidth(version, g);
		g.drawString(version, GamePanel.WIDTH - (versionWidth + 5), GamePanel.HEIGHT - 5);
	}
	
	/**
	 * Method that draws the Buttons for the PlayState
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawButtons(Graphics2D g)
	{
		//Draw Buttons
		slideLeftButton.draw(g);
		slideRightButton.draw(g);
		slideUpButton.draw(g);
		slideDownButton.draw(g);
		horizontalSplitButton.draw(g);
		verticalSplitButton.draw(g);
		fadeToBlackButton.draw(g);
		fadeToWhiteButton.draw(g);
	}
	
	/**
	 * Method that draws the Transition
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawTransition(Graphics2D g)
	{
		//Draw transition
		if(transitionManager.isRunning())
		{
			transitionManager.draw(g);
		}
	}
	
	/**
	 * Method that draws everything within the PlayState
	 * 
	 * @param g The Graphics2D object that is going to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		//Draw Background
		drawBackground(g);
		
		//Draw Strings
		drawStrings(g);
		
		//Draw Buttons
		drawButtons(g);
		
		//Draw Transition
		drawTransition(g);
	}
}
