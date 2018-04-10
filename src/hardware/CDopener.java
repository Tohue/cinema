package hardware;

import java.io.IOException;

public class CDopener {

    public static void openDrive() {

        try {
            Runtime.getRuntime().exec("resources\\nircmd.exe cdrom open D:");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
