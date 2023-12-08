package ninjabrainbot.model.datastate.endereye;

import ninjabrainbot.io.preferences.NinjabrainBotPreferences;

public class InputData1_12 {

	public final double x, z, horizontalAngle;

	private InputData1_12(double x, double z, double horizontalAngle) {
		this.x = x;
		this.z = z;
		this.horizontalAngle = horizontalAngle;
	}

	public static InputData1_12 parseInputString(String string, NinjabrainBotPreferences preferences) {
		String[] substrings = string.split(" ");
		if (substrings.length != 3)
			return null;
		try {
			double x = Double.parseDouble(substrings[0]) + (preferences.blockCoordsManual.get() ? 0.0 : 0.5);
			double z = Double.parseDouble(substrings[1]) + (preferences.blockCoordsManual.get() ? 0.0 : 0.5);
			double rawAlpha = Double.parseDouble(substrings[2]);
			return new InputData1_12(x, z, rawAlpha);
		} catch (NullPointerException | NumberFormatException e) {
			return null;
		}
	}

}
