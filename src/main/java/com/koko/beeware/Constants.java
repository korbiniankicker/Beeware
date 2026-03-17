package com.koko.beeware;

public final class Constants {
	private Constants() {}

	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 922;

	public static final int GRID_SIZE = 15;
	public static final int TILE_SIZE = 60;

	/** Fixed update tick in milliseconds. */
	public static final int TICK_MS = 10;

	/** Default length of a wave in ticks (decremented once per tick). */
	public static final int WAVE_LENGTH_TICKS = 5000;

	/** Max pooled entity slots used by the game state. */
	public static final int MAX_ENTITIES = 100;

	/** Number of UI button hover slots actually used. */
	public static final int UI_BUTTON_SLOTS = 10;

	/** Mouse Y offset to account for window title bar. */
	public static final int MOUSE_Y_OFFSET = 27;

	/** Simple HUD wave bar. */
	public static final int HUD_WAVE_BAR_X = 10;
	public static final int HUD_WAVE_BAR_Y = 10;
	public static final int HUD_WAVE_BAR_W = 100;
	public static final int HUD_WAVE_BAR_H = 20;
}

