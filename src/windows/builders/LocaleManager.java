package windows.builders;



import java.util.Locale;

public class LocaleManager {

    private static Locale currentLocale = Locale.getDefault();

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        LocaleManager.currentLocale = currentLocale;
    }


}
