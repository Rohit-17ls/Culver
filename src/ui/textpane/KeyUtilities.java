package ui.textpane;

public class KeyUtilities {
    // TODO : Verify Keycodes -> 3, 12, 21, 24, 25, 28, 29, 30, 31
    private static final Integer[] neglectableKeyCodes = new Integer[]{3, 12, 17, 18, 19, 20, 21, 24, 25, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40,
            112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 144};


    public static boolean isNeglectableKeyCode(int keyCode){
        for (Integer neglectableKeyCode : neglectableKeyCodes) {
            if (keyCode == neglectableKeyCode) return true;
        }

        return false;
    }

}
