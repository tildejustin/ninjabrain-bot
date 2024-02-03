package ninjabrainbot.model.datastate.endereye;

import ninjabrainbot.model.datastate.common.IDetailedPlayerPosition;
import ninjabrainbot.model.environmentstate.StandardDeviationSettings;

/**
 * Represents an eye of ender throw, from a normal F3+C command.
 */
public class NormalEnderEyeThrow extends EnderEyeThrow {

	private final boolean altStandardDeviation;

	public NormalEnderEyeThrow(IDetailedPlayerPosition detailedPlayerPosition, double crosshairCorrection) {
		this(detailedPlayerPosition.xInOverworld(), detailedPlayerPosition.zInPlayerDimension(), getCorrectedHorizontalAngle(detailedPlayerPosition.horizontalAngle(), crosshairCorrection),
				detailedPlayerPosition.verticalAngle(), 0, false);
	}

	private NormalEnderEyeThrow(double x, double z, double horizontalAngle, double verticalAngle, double correction, boolean altStandardDeviation) {
		super(x, z, horizontalAngle, verticalAngle, correction);
		this.altStandardDeviation = altStandardDeviation;
	}

	@Override
	public IEnderEyeThrow withCorrection(double correction) {
		return new NormalEnderEyeThrow(x, z, horizontalAngleWithoutCorrection, verticalAngle, correction, altStandardDeviation);
	}

	@Override
	public IEnderEyeThrow withToggledAltStd() {
		return new NormalEnderEyeThrow(x, z, horizontalAngleWithoutCorrection, verticalAngle, correction, !altStandardDeviation);
	}

	@Override
	public double getStandardDeviation(StandardDeviationSettings standardDeviationSettings) {
		return altStandardDeviation ? standardDeviationSettings.altStd : standardDeviationSettings.std;
	}

	@Override
	public double getExpectedStandardDeviationForNextEnderEyeThrow(StandardDeviationSettings standardDeviationSettings) {
		return standardDeviationSettings.std;
	}

	@Override
	public EnderEyeThrowType getType() {
		return altStandardDeviation ? EnderEyeThrowType.NormalWithAltStd : EnderEyeThrowType.Normal;
	}

	private static double getCorrectedHorizontalAngle(double alpha, double crosshairCorrection) {
		alpha += crosshairCorrection;

		// Caused by rounding in client-bound move entity packets
		alpha -= 0.000824 * Math.sin((alpha + 45) * Math.PI / 180.0);

		return alpha;
	}

}
