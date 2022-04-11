import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * ImageManager class attempts to load image files and keeps track 
 * of them
 * 
 * @author Vachia Thoj
 *
 */
public class ImageManager 
{
	//For singleton
	private static ImageManager imageManager;
	
	//To store Button images
	private BufferedImage[] buttonImages;
	
	//Constructor (private)
	private ImageManager()
	{
		BufferedImage buttonSheet = loadImage("images/ButtonSheet.png");
		
		loadButtonImages(buttonSheet);
		
		buttonSheet = null;
	}
	
	/**
	 * Method to be called to obtain ImageManager object (Singleton)
	 * @return ImageManager object 
	 */
	public static ImageManager instance()
	{
		if(imageManager == null)
		{
			imageManager = new ImageManager();
		}
		
		return imageManager;
	}
	
	/**
	 * Method that attempts to open/obtain an image
	 * 
	 * @param address String of the address location of the image
	 * @return BufferedImage from the address provided, will return null if image cannot be found opened
	 */
	private BufferedImage loadImage(String address)
	{
		BufferedImage imageSheet = null;
		
		//Try to obtain an image
		try {
			//Obtain image from address
			imageSheet = ImageIO.read(getClass().getResourceAsStream(address));
			
			return imageSheet;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading image");
			System.exit(0);
		}
		return null;
	}
	
	/**
	 * Method that obtains individual Button images (subimages) from an image sheet
	 * 
	 * @param sheet The BufferedImage to obtain subimages from
	 */
	private void loadButtonImages(BufferedImage sheet)
	{
		buttonImages = new BufferedImage[16];
		int index = 0;
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				//Obtain button images from image sheet
				buttonImages[index] = sheet.getSubimage(j * 192, i * 64, 192, 64);
				++index;
			}
		}
	}
	
	//Getter methods
	public BufferedImage[] getButtonImages() {return buttonImages;}
}
