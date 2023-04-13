package ninjabrainbot.model.datastate.alladvancements;

import ninjabrainbot.event.DisposeHandler;
import ninjabrainbot.event.IDisposable;
import ninjabrainbot.event.IObservable;
import ninjabrainbot.event.ObservableField;
import ninjabrainbot.model.datastate.common.StructurePosition;
import ninjabrainbot.model.datastate.stronghold.ChunkPrediction;
import ninjabrainbot.model.domainmodel.DataComponent;
import ninjabrainbot.model.domainmodel.IDataComponent;
import ninjabrainbot.model.domainmodel.IDomainModel;
import ninjabrainbot.model.environmentstate.IEnvironmentState;

public class AllAdvancementsDataState implements IAllAdvancementsDataState, IDisposable {

	private final IObservable<ChunkPrediction> currentStrongholdPrediction;
	private final IEnvironmentState environmentState;

	private final ObservableField<Boolean> allAdvancementsModeEnabled;
	private final ObservableField<StructurePosition> strongholdPosition;
	private final DataComponent<StructurePosition> spawnPosition;
	private final DataComponent<StructurePosition> outpostPosition;
	private final DataComponent<StructurePosition> monumentPosition;

	private final DisposeHandler disposeHandler = new DisposeHandler();

	public AllAdvancementsDataState(IObservable<ChunkPrediction> currentStrongholdPrediction, IDomainModel domainModel, IEnvironmentState environmentState) {
		this.currentStrongholdPrediction = currentStrongholdPrediction;
		this.environmentState = environmentState;
		allAdvancementsModeEnabled = new ObservableField<>(false);
		strongholdPosition = new ObservableField<>(null);
		spawnPosition = new DataComponent<>(domainModel);
		outpostPosition = new DataComponent<>(domainModel);
		monumentPosition = new DataComponent<>(domainModel);
		disposeHandler.add(environmentState.allAdvancementsModeEnabled().subscribe(this::updateAllAdvancementsMode));
		disposeHandler.add(environmentState.hasEnteredEnd().subscribe(this::updateAllAdvancementsMode));
	}

	private void updateAllAdvancementsMode() {
		boolean enabled = environmentState.allAdvancementsModeEnabled().get() && environmentState.hasEnteredEnd().get();
		allAdvancementsModeEnabled.set(enabled);
		if (enabled)
			strongholdPosition.set(currentStrongholdPrediction.get());
	}

	@Override
	public IObservable<Boolean> allAdvancementsModeEnabled() {
		return allAdvancementsModeEnabled;
	}

	@Override
	public IObservable<StructurePosition> strongholdPosition() {
		return strongholdPosition;
	}

	@Override
	public IDataComponent<StructurePosition> spawnPosition() {
		return spawnPosition;
	}

	@Override
	public IDataComponent<StructurePosition> outpostPosition() {
		return outpostPosition;
	}

	@Override
	public IDataComponent<StructurePosition> monumentPosition() {
		return monumentPosition;
	}

	@Override
	public void dispose() {
		disposeHandler.dispose();
	}
}