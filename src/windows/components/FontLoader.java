package windows.components;

import javafx.scene.text.Font;

public class FontLoader {

    public Font getLemon(int size) {
        return Font.loadFont(getClass().getResource("/fonts/lemon.otf").toExternalForm(), size);
    }

    public Font getBebasThin(int size) {
        return Font.loadFont(getClass().getResource("/fonts/Bebas/BebasNeueThin.otf").toExternalForm(), size);
    }

    public Font getBebasReg(int size) {
        return Font.loadFont(getClass().getResource("/fonts/Bebas/BebasNeueRegular.otf").toExternalForm(), size);
    }

}
