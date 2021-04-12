public class KeyState {
    private boolean[] states = new boolean[256];
    private boolean[] oldStates = new boolean[256];

    public void setKeyState(int keyCode, boolean state) {
        states[keyCode] = state;
    }

    public void update() {
        oldStates = (boolean[]) states.clone();
    }

    public boolean keyDown(int keyCode) {
        return states[keyCode];
    }
}
