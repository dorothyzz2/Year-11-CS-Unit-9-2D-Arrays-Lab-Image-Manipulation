package code;

import image.Pixel;
import image.APImage;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        String fileName = "cyberpunk2077.jpg";
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
        grayScale(fileName);
        blackAndWhite(fileName);
        edgeDetection(fileName, 30);
        reflectImage(fileName);
        rotateImage(fileName);
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        for(int x = 0; x<image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                Pixel pixel = image.getPixel(x,y);
                int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                pixel.setBlue(average);
                pixel.setGreen(average);
                pixel.setRed(average);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        for(int x = 0; x<image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                Pixel pixel = image.getPixel(x, y);
                int average = getAverageColour(pixel); 
                if(average < 128){
                    pixel.setBlue(0);
                    pixel.setGreen(0);
                    pixel.setRed(0);
                } else {
                    pixel.setBlue(255);
                    pixel.setGreen(255);
                    pixel.setRed(255);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage result = image.clone();
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Pixel thisPixel = image.getPixel(x, y);
                int thisAvg = getAverageColour(thisPixel);
                int leftAvg = thisAvg; 
                if (x > 0){ 
                    Pixel leftPixel = image.getPixel(x - 1, y);
                    leftAvg = getAverageColour(leftPixel);
                }
                int belowAvg = thisAvg;  
                if (y < image.getHeight() - 1){ 
                    Pixel belowPixel = image.getPixel(x, y + 1);
                    belowAvg = getAverageColour(belowPixel);
                }
                Pixel resultPixel = result.getPixel(x, y);
                if (Math.abs(thisAvg - leftAvg) > threshold || Math.abs(thisAvg - belowAvg) > threshold) {
                    resultPixel.setBlue(0);
                    resultPixel.setGreen(0);
                    resultPixel.setRed(0);
                } else {
                    resultPixel.setBlue(255);
                    resultPixel.setGreen(255);
                    resultPixel.setRed(255);
            }
            }
        }
        result.draw();
    }

    /** CHALLENGE Four: Reflect Image
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage reflected = new APImage(image.getWidth(), image.getHeight());
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Pixel thisPixel = image.getPixel(x, y);
                int xReflection = image.getWidth() - 1 - x;
                int yReflection = y;
                Pixel reflectedPixel = reflected.getPixel(xReflection, yReflection);
                reflectedPixel.setBlue(thisPixel.getBlue());
                reflectedPixel.setGreen(thisPixel.getGreen());
                reflectedPixel.setRed(thisPixel.getRed());
            }
        }
        reflected.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int newWidth = image.getHeight();
        int newHeight = image.getWidth();
        APImage rotated = new APImage(newWidth, newHeight);
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Pixel originalPixel = image.getPixel(x, y);
                int xNew = image.getHeight() - 1 - y;
                int yNew = x; 
                Pixel rotatedPixel = rotated.getPixel(xNew, yNew);
                rotatedPixel.setBlue(originalPixel.getBlue());
                rotatedPixel.setGreen(originalPixel.getGreen());
                rotatedPixel.setRed(originalPixel.getRed());
            }
        }
        rotated.draw();
    }
}
