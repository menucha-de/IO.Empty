package havis.device.io.empty;

import havis.device.io.Direction;
import havis.device.io.State;
import havis.device.io.StateListener;
import havis.device.io.common.HardwareManager;

/**
 * Hardware manager if no IO is present
 */
public class EmptyHardwareManager implements HardwareManager {

	@Override
	public State getState(short id) {
		return State.UNKNOWN;
	}

	@Override
	public void setState(short id, State state) throws IllegalArgumentException {
	}

	@Override
	public Direction getDirection(short id) {
		return null;
	}

	@Override
	public void setDirection(short id, Direction direction) {
	}

	@Override
	public boolean getEnable(short id) throws IllegalArgumentException {
		return false;
	}

	@Override
	public void setEnable(short id, boolean enable) throws IllegalArgumentException {
	}

	@Override
	public short getCount() {
		return 0;
	}

	@Override
	public void setListener(StateListener listener) {
	}
}
