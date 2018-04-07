package hardware;

import java.io.IOException;

public class CDopener {

    public static void openDrive() {

        try {
            Runtime.getRuntime().exec("resources\\nircmd.exe cdrom open F:");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
