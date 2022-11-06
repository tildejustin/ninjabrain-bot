package ninjabrainbot.gui.style;

import ninjabrainbot.event.ISubscribable;
import ninjabrainbot.event.ObservableProperty;
import ninjabrainbot.util.Wrapper;

public class CurrentTheme {

	public final WrappedColor COLOR_STRONGEST = new WrappedColor();
	public final WrappedColor COLOR_DIVIDER = new WrappedColor();
	public final WrappedColor COLOR_DIVIDER_DARK = new WrappedColor();
	public final WrappedColor COLOR_STRONG = new WrappedColor();
	public final WrappedColor COLOR_SLIGHTLY_STRONG = new WrappedColor();
	public final WrappedColor COLOR_NEUTRAL = new WrappedColor();
	public final WrappedColor COLOR_SLIGHTLY_WEAK = new WrappedColor();
	public final WrappedColor COLOR_EXIT_BUTTON_HOVER = new WrappedColor();
	public final WrappedColor TEXT_COLOR_STRONG = new WrappedColor();
	public final WrappedColor TEXT_COLOR_SLIGHTLY_STRONG = new WrappedColor();
	public final WrappedColor TEXT_COLOR_NEUTRAL = new WrappedColor();
	public final WrappedColor TEXT_COLOR_WEAK = new WrappedColor();
	public final WrappedColor COLOR_SATURATED = new WrappedColor();
	public final WrappedColor COLOR_POSITIVE = new WrappedColor();
	public final WrappedColor COLOR_NEGATIVE = new WrappedColor();

	public final Wrapper<Boolean> BLACK_ICONS = new Wrapper<>();
	public final Wrapper<ColorMap> CERTAINTY_COLOR_MAP = new Wrapper<>();

	private ObservableProperty<CurrentTheme> whenModified = new ObservableProperty<CurrentTheme>();

	public void setTheme(Theme theme) {
		COLOR_STRONGEST.set(theme.COLOR_STRONGEST);
		COLOR_DIVIDER.set(theme.COLOR_DIVIDER);
		COLOR_DIVIDER_DARK.set(theme.COLOR_DIVIDER_DARK);
		COLOR_STRONG.set(theme.COLOR_STRONG);
		COLOR_SLIGHTLY_STRONG.set(theme.COLOR_SLIGHTLY_STRONG);
		COLOR_NEUTRAL.set(theme.COLOR_NEUTRAL);
		COLOR_SLIGHTLY_WEAK.set(theme.COLOR_SLIGHTLY_WEAK);
		COLOR_EXIT_BUTTON_HOVER.set(theme.COLOR_EXIT_BUTTON_HOVER);
		TEXT_COLOR_STRONG.set(theme.TEXT_COLOR_STRONG);
		TEXT_COLOR_SLIGHTLY_STRONG.set(theme.TEXT_COLOR_SLIGHTLY_STRONG);
		TEXT_COLOR_NEUTRAL.set(theme.TEXT_COLOR_NEUTRAL);
		TEXT_COLOR_WEAK.set(theme.TEXT_COLOR_WEAK);
		COLOR_SATURATED.set(theme.COLOR_SATURATED);
		COLOR_POSITIVE.set(theme.COLOR_POSITIVE);
		COLOR_NEGATIVE.set(theme.COLOR_NEGATIVE);

		BLACK_ICONS.set(theme.BLACK_ICONS);
		CERTAINTY_COLOR_MAP.set(theme.CERTAINTY_COLOR_MAP);

		whenModified.notifySubscribers(this);
	}

	public ISubscribable<CurrentTheme> whenModified() {
		return whenModified;
	}

}
