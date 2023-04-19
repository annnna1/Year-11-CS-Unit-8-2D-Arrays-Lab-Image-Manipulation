package code;

import image.Pixel;

import image.APImage;

import java.util.ArrayList;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args)
    {
        APImage image = new APImage ("cyberpunk2077.jpg");
        //image.draw();
        //grayScale("cyberpunk2077.jpg");
        //blackAndWhite("cyberpunk2077.jpg");
        //edgeDetection("cyberpunk2077.jpg" , 20);
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile)
    {
        APImage image = new APImage (pathOfFile);
        for(int i = 0 ; i < image.getHeight() ; i ++ )
        {
            for(int j = 0 ; j < image.getWidth() ; j ++)
            {
                Pixel pixel = image.getPixel(j , i);
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                int avg = (red+green+blue)/3;
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
                image.setPixel(j , i , pixel);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel)
    {
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int avg = (red+green+blue)/3;
        return avg;

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
    public static void blackAndWhite(String pathOfFile)
    {
        APImage image = new APImage (pathOfFile);
        for(int i = 0 ; i < image.getHeight() ; i ++ )
        {
            for(int j = 0 ; j < image.getWidth() ; j ++)
            {
                Pixel pixel = image.getPixel(j , i);
                int avg = getAverageColour(pixel);
                int pixelNum;
                if (avg<128)
                {
                    pixelNum = 0;
                }
                else
                {
                    pixelNum = 255;
                }
                Pixel pix = new Pixel (pixelNum , pixelNum , pixelNum);
                image.setPixel(j , i , pix);
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold)
    {
        APImage image = new APImage(pathToFile);
        int h = image.getHeight();
        int w = image.getWidth();
        Pixel[][] pAr = new Pixel[h][w];

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {

                Pixel p = image.getPixel(j, i);
                int n;
                int a1 = getAverageColour(p);
                int a2 = a1;
                int a3 = a1;
                if (!(i == image.getHeight() - 1))
                {
                    a3 = getAverageColour(image.getPixel(j, i + 1));
                }
                if (!(j == 0))
                {
                    a2 = getAverageColour(image.getPixel(j - 1, i));
                }
                if ((a1 - a2 <= threshold) && (a2 - a1 <= threshold) && (a1 - a3 <= threshold) && (a3 - a1 <= threshold))
                {
                    n = 255;
                }
                else
                {
                    n = 0;
                }
                pAr[i][j] = new Pixel (n,n,n);
            }
        }
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                image.setPixel(j, i, pAr[i][j]);
            }
        }
        image.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {

    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {

    }

}
