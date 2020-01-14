/*
 * Pranav Sukumar
 * ElectoralMap.java
 * This class contains the Electoral Map Class for the Red vs. Blue Project
 * No Revisions
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElectoralMap {
	public void visualize(String string, int year) {
		if (!string.equals("USA-county")) {
			visualizeState(string, year, true);
		} else {
			visualizeCountryMap(string, year);
		}

	}

	private void visualizeState(String string, int year, boolean resize) throws NullPointerException {
		try {
			FileReader geoFile = new FileReader("./input/" + string + ".txt");
			BufferedReader geoBr = new BufferedReader(geoFile);

			// Scaling Canvas Size
			String[] minCoords = geoBr.readLine().split("   ");
			double minX = Double.parseDouble(minCoords[0]);
			double minY = Double.parseDouble(minCoords[1]);
			String[] maxCoords = geoBr.readLine().split("   ");
			double maxX = Double.parseDouble(maxCoords[0]);
			double maxY = Double.parseDouble(maxCoords[1]);
			double xDiff = (maxX - minX);
			double yDiff = (maxY - minY);
			if (resize){
				int scaleWidth = (int) ((xDiff * 512) / (yDiff));
				StdDraw.setCanvasSize(scaleWidth, 512);
				StdDraw.setXscale(minX, maxX);
				StdDraw.setYscale(minY, maxY);
			}

			StdDraw.enableDoubleBuffering();

			Map<String, RegionData> map = new HashMap<String, RegionData>();

			FileReader electionFr = new FileReader("./input/" + string + year + ".txt");
			BufferedReader electionBr = new BufferedReader(electionFr);

			electionBr.readLine();
			String line = electionBr.readLine();
			while (line != null) {
				String[] data = line.split(",");
				RegionData rd = new RegionData(Integer.parseInt(data[2]), Integer.parseInt(data[1]),
						Integer.parseInt(data[3]));
				String name = data[0].toLowerCase();
				map.put(name, rd);

				line = electionBr.readLine();
			}
			electionFr.close();
			electionBr.close();

			int n = Integer.parseInt(geoBr.readLine());
			for (int i = 0; i < n; i++) {
				geoBr.readLine();
				String countyName = geoBr.readLine();
				geoBr.readLine();
				int countySize = Integer.parseInt(geoBr.readLine());
				double[] xCoords = new double[countySize];
				double[] yCoords = new double[countySize];
				for (int j = 0; j < countySize; j++) {
					String[] individualPoint = geoBr.readLine().split("   ");
					xCoords[j] = Double.parseDouble(individualPoint[0]);
					yCoords[j] = Double.parseDouble(individualPoint[1]);
				}
				countyName = countyName.replace(" Parish", "");
				countyName = countyName.replace(" city", "");
				if (string.equals("FL")){
					countyName = countyName.replace("Dade", "Miami-Dade");
				}
				countyName = countyName.toLowerCase();
				if (map.containsKey(countyName)) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.setPenRadius(.005);
					StdDraw.polygon(xCoords, yCoords);

					int dVotes = map.get(countyName).getD();
					int rVotes = map.get(countyName).getR();
					int iVotes = map.get(countyName).getI();

					int largest = Math.max(dVotes, Math.max(rVotes, iVotes));
					if (largest == dVotes) {
						StdDraw.setPenColor(StdDraw.BLUE);
						StdDraw.filledPolygon(xCoords, yCoords);
					} else if (largest == rVotes) {
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.filledPolygon(xCoords, yCoords);
					} else if (largest == iVotes) {
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledPolygon(xCoords, yCoords);
					}
				}
			}
			StdDraw.show();

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

	private void visualizeCountryMap(String string, int year) throws NullPointerException {
		try {
			FileReader geoFile = new FileReader("./input/" + string + ".txt");
			BufferedReader geoBr = new BufferedReader(geoFile);

			// Scaling Canvas Size
			String[] minCoords = geoBr.readLine().split("   ");
			double minX = Double.parseDouble(minCoords[0]);
			double minY = Double.parseDouble(minCoords[1]);
			String[] maxCoords = geoBr.readLine().split("   ");
			double maxX = Double.parseDouble(maxCoords[0]);
			double maxY = Double.parseDouble(maxCoords[1]);
			double xDiff = (maxX - minX);
			double yDiff = (maxY - minY);
			int scaleWidth = (int) ((xDiff * 512) / (yDiff));
			StdDraw.setCanvasSize(scaleWidth, 512);
			StdDraw.setXscale(minX, maxX);
			StdDraw.setYscale(minY, maxY);
			StdDraw.enableDoubleBuffering();

			String[] states = { "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "ID", "IL", "IN", "IA", "KS",
					"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
					"NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
					"WI", "WY" };
			for (String s : states) {
				visualizeState(s, year, false);
			}
			StdDraw.show();

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