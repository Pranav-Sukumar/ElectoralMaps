/*
 * Pranav Sukumar
 * RegionData.java
 * This class contains a helper class I wrote for the Red vs. Blue Project to store the votes of a subregion
 * No Revisions
 */

public class RegionData {
	private int demVotes;
	private int repVotes;
	private int indVotes;

	public RegionData(int d, int r, int i) {
		demVotes = d;
		repVotes = r;
		indVotes = i;
	}

	public int getD() {
		return demVotes;
	}

	public int getR() {
		return repVotes;
	}

	public int getI() {
		return indVotes;
	}
}
