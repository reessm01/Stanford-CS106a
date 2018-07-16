
/* 
 * File Name: ImageShopAlgorithms.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:8/4/2017
 * Citation: 
 * Agolrithm's sequencing through 2d arrays was modeled after Nick Trocolli's 
 * lectures on the topic.
 * 
 * These are a series of algorithms that achieve the following:
 * Negative, green screen, rotate left, rotate right, flip horizontal, translate,
 * blur and equalize. For specifics on what each of these do, please refer to
 * their respective methods.
 */

import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	// flipHorizontal() takes a source image and mirrors its along a centered
	// vertical axis.

	public GImage flipHorizontal(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[rowLength][columnLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int pixel = pixels[r][c];
				int newLoc = horizontalMove(c, columnLength);

				newPixels[r][newLoc] = pixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// horizontalMove() takes the parameters of a column location and column
	// length and returns its mirrored vertical location.

	private int horizontalMove(int c, int columnLength) {
		int middle = columnLength / 2 + 1;
		int newLoc = columnLength - 1 - c;
		if (columnLength % 2 == 0 || c != middle) {
			return newLoc;
		} else {
			return c;
		}
	}

	// rotateLeft() takes an image source and rotates it counterclockwise by 90
	// degrees.

	public GImage rotateLeft(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[columnLength][rowLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int pixel = pixels[r][c];
				int newLoc = movePixel(c, rowLength);

				newPixels[newLoc][r] = pixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// movePixel() takes the parameters of the column or row location and max
	// row or column size, and computes and returns a new location for the
	// pixel.

	private int movePixel(int c, int arrayLength) {
		int newLoc = arrayLength - 1 - c;
		return newLoc;
	}

	// rotateRight() takes an image source and rotates it clockwise by 90
	// degrees.

	public GImage rotateRight(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[columnLength][rowLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int pixel = pixels[r][c];
				int newLoc = movePixel(r, columnLength);

				newPixels[c][newLoc] = pixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// greenScreen() takes an image source and systematically processes it to
	// remove a green screen background and returns the new image.

	public GImage greenScreen(GImage source) {
		int pixels[][] = source.getPixelArray();

		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[0].length; c++) {
				int pixel = pixels[r][c];

				int red = GImage.getRed(pixel);
				int green = GImage.getGreen(pixel);
				int blue = GImage.getBlue(pixel);

				int newPixel = removeGreenOnly(red, green, blue);
				pixels[r][c] = newPixel;
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	// removeGreenOnly() takes in the parameters for the RGB values of a pixel
	// and decides if it is a green screen pixel or not. It then returns the
	// appropriate pixel.

	private int removeGreenOnly(int red, int green, int blue) {
		double bigger = Math.max(red, blue);
		double ratio = green / bigger;
		if (ratio >= 2) {
			int transparentPixel = GImage.createRGBPixel(1, 1, 1, 0);
			return transparentPixel;
		} else {
			int regularPixel = GImage.createRGBPixel(red, green, blue);
			return regularPixel;
		}
	}

	// equalize() takes in a source image as a parameter and applies a histogram
	// equalization.

	public GImage equalize(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[rowLength][columnLength];
		int[] lumArray = new int[256];
		int[] cumLumArray = new int[256];
		int totalPixels = rowLength * columnLength;

		lumArray = luminosityHistogram(lumArray, pixels);
		cumLumArray = cumLuminosityHistogram(lumArray, cumLumArray);
		newPixels = modifyPixels(cumLumArray, pixels, totalPixels, newPixels);

		GImage image = new GImage(newPixels);
		return image;
	}

	// luminosityHistogram() takes the parameters of a blank lumArray and the
	// array of pixels, and outputs those pixels into the lumArray acting as the
	// histogram of luminosity.

	private int[] luminosityHistogram(int lumArray[], int pixels[][]) {
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[0].length; c++) {
				int pixel = pixels[r][c];
				int red = GImage.getRed(pixel);
				int green = GImage.getGreen(pixel);
				int blue = GImage.getBlue(pixel);
				int luminosity = computeLuminosity(red, green, blue);
				lumArray[luminosity]++;
			}
		}
		return lumArray;
	}

	// cumLuminosityHistogram() takes the lumArray, blank cumLumArray and
	// outputs the cumulative histogram into the cumLumArray.

	private int[] cumLuminosityHistogram(int lumArray[], int cumLumArray[]) {
		int cumulative = 0;
		for (int i = 0; i < lumArray.length; i++) {
			if (i == 0) {
				cumLumArray[i] = lumArray[i];
				cumulative += lumArray[i];
			} else {
				cumulative += lumArray[i];
				cumLumArray[i] = cumulative;
			}
		}
		return cumLumArray;
	}

	// modifyPixels() takes the inputs of cumLumArray, image pixels,
	// totalPixels, and a blank newPixels array and processes the new contrast
	// of each pixel. The returned value is an array of pixels with the modified
	// contrast.

	private int[][] modifyPixels(int cumLumArray[], int pixels[][], int totalPixels, int newPixels[][]) {
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[0].length; c++) {
				int pixel = pixels[r][c];
				int red = GImage.getRed(pixel);
				int green = GImage.getGreen(pixel);
				int blue = GImage.getBlue(pixel);
				int luminosity = computeLuminosity(red, green, blue);
				int lumosTotal = cumLumArray[luminosity];
				int newRed = increaseContrast(lumosTotal, totalPixels);
				int newGreen = increaseContrast(lumosTotal, totalPixels);
				int newBlue = increaseContrast(lumosTotal, totalPixels);
				int newPixel = GImage.createRGBPixel(newRed, newGreen, newBlue);
				newPixels[r][c] = newPixel;
			}
		}
		return newPixels;
	}

	private int increaseContrast(int lumosTotal, int totalPixels) {
		return 255 * lumosTotal / totalPixels;
	}

	// negative() systematically inverts every pixel and returns a new image
	// with those pixels.

	public GImage negative(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[rowLength][columnLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int pixel = pixels[r][c];

				int red = invertColor(GImage.getRed(pixel));
				int green = invertColor(GImage.getGreen(pixel));
				int blue = invertColor(GImage.getBlue(pixel));

				int newPixel = GImage.createRGBPixel(red, green, blue);
				newPixels[r][c] = newPixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// invertColor() inverts the parameter and returns it.

	private int invertColor(int color) {
		color = 255 - color;
		return color;
	}

	// translate() takes the parameters of a source image file, change in x and
	// y location, and outputs the source image with the change in location.
	// Exceeding canvas size causes the image to wrap around.

	public GImage translate(GImage source, int dx, int dy) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[rowLength][columnLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int pixel = pixels[r][c];

				int newR = translateMove(r, dy, rowLength);
				int newC = translateMove(c, dx, columnLength);

				newPixels[newR][newC] = pixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// translateMove() takes the parameters of the column or row location,
	// desired change in location, and max length of the row or column and
	// outputs the new row or column pixel location. Note: changes in location
	// wrap.

	private int translateMove(int loc, int delta, int maxLength) {
		int newLoc = loc + delta;
		int max = Math.max(newLoc, maxLength - 1);
		if (max == newLoc) {
			return newLoc %= maxLength;
		} else if (newLoc >= 0) {
			return newLoc;
		} else {
			return newLoc = maxLength + newLoc;
		}
	}

	// blur() takes a source image and outputs a blurred version.

	public GImage blur(GImage source) {
		int pixels[][] = source.getPixelArray();
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int newPixels[][] = new int[rowLength][columnLength];

		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < columnLength; c++) {
				int newPixel = averageColor(r, c, pixels);
				newPixels[r][c] = newPixel;
			}
		}
		GImage image = new GImage(newPixels);
		return image;
	}

	// averageColor() takes the inputs of a single pixel from the source, and
	// the source's length of its rows. and columns. From these parameters it
	// processes them into the average red, green, blue integers from the 8
	// closest pixels including the origin pixel and outputs a new pixel with
	// these
	// averages.

	private int averageColor(int r, int c, int pixels[][]) {
		int rowLength = pixels.length;
		int columnLength = pixels[0].length;
		int counter = 0;
		int rMin = r - 1;
		int rMax = r + 2;
		int cMin = c - 1;
		int cMax = c + 2;
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int column = cMin; column < cMax; column++) {
			for (int row = rMin; row < rMax; row++) {
				boolean columnBool = (column >= 0 && column <= cMax && column < columnLength);
				boolean rowBool = (row >= 0 && row <= rMax && row < rowLength);
				if (columnBool && rowBool) {
					int pixel = pixels[row][column];
					red += GImage.getRed(pixel);
					green += GImage.getGreen(pixel);
					blue += GImage.getBlue(pixel);
					counter++;
				}
			}
		}
		red /= counter;
		green /= counter;
		blue /= counter;
		int newPixel = GImage.createRGBPixel(red, green, blue);
		return newPixel;
	}
}
