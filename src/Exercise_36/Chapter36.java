package Exercise_36;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;

public class Chapter36 {
    private static final Map<String, Map<String, String>> MESSAGES = Map.of(
            "en", Map.of("greeting", "Hello", "farewell", "Goodbye"),
            "de", Map.of("greeting", "Hallo", "farewell", "Auf Wiedersehen"),
            "ar", Map.of("greeting", "مرحبا", "farewell", "إلى اللقاء"));

    public static String formatDate(LocalDate date, String language) {
        if (date == null) {
            throw new IllegalArgumentException("date is required");
        }
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(localeFor(language)));
    }

    public static String message(String language, String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is required");
        }
        Map<String, String> bundle = MESSAGES.getOrDefault(localeFor(language).getLanguage(),
                MESSAGES.get("en"));
        return bundle.getOrDefault(key, "!" + key + "!");
    }

    public static String formatCurrency(double amount, String language) {
        return NumberFormat.getCurrencyInstance(localeFor(language)).format(amount);
    }

    private static Locale localeFor(String language) {
        if (language == null || language.isBlank()) {
            return Locale.US;
        }
        return switch (language.trim().toLowerCase(Locale.ROOT)) {
            case "de" -> Locale.GERMANY;
            case "ar" -> Locale.forLanguageTag("ar");
            default -> Locale.US;
        };
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2026, 11, 16);
        System.out.println(formatDate(date, "en"));
        System.out.println(formatDate(date, "de"));
        System.out.println(message("ar", "greeting"));
        System.out.println(formatCurrency(42.5, "de"));
    }
}
