/*
 * Pranav Sukumar
 * EmptyMap.java
 * This class contains the Empty Map Class for the Red vs. Blue Project
 * No Revisions
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmptyMap {

	public void visualize(String string) {
		try {
			FileReader fr = new FileReader("./input/" + string + ".txt");
			BufferedReader br = new BufferedReader(fr);

			String[] minCoords = br.readLine().split("   ");
			double minX = Double.parseDouble(minCoords[0]);
			double minY = Double.parseDouble(minCoords[1]);
			String[] maxCoords = br.readLine().split("   ");
			double maxX = Double.parseDouble(maxCoords[0]);
			double maxY = Double.parseDouble(maxCoords[1]);

			double xDiff = (maxX - minX);
			double yDiff = (maxY - minY);
			int scaleWidth = (int) ((xDiff * 512) / (yDiff));
			StdDraw.setCanvasSize(scaleWidth, 512);
			StdDraw.setXscale(minX, maxX);
			StdDraw.setYscale(minY, maxY);
			StdDraw.enableDoubleBuffering();

			int regions = Integer.parseInt(br.readLine());
			for (int i = 0; i < regions; i++) {
				for (int j = 0; j < 3; j++) {
					br.readLine();
				}
				int points = Integer.parseInt(br.readLine());
				double[] xCoords = new double[points];
				double[] yCoords = new double[points];
				for (int j = 0; j < points; j++) {
					String[] individualPoint = br.readLine().split("   ");
					xCoords[j] = Double.parseDouble(individualPoint[0]);
					yCoords[j] = Double.parseDouble(individualPoint[1]);
					//System.out.println(xCoords[j] + " " + yCoords[j]);
				}
				StdDraw.polygon(xCoords, yCoords);

			}
			StdDraw.show();
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound Error");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("FileNotFoundError");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException Error");
			e.printStackTrace();
		}
	}

}