package atlas.enterprise;

import java.text.Collator;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public final class LocaleTour {
    private LocaleTour() {
    }

    public static String checkoutMessage(Locale locale, int items, double total) {
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);
        String template = bundle.getString("checkout");
        String amount = NumberFormat.getCurrencyInstance(locale).format(total);
        return template.formatted(items, amount);
    }

    public static String formattedNow(Locale locale, ZoneId zone) {
        return ZonedDateTime.now(zone)
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
    }

    public static List<String> sortedNames(List<String> names, Locale locale) {
        Collator collator = Collator.getInstance(locale);
        return names.stream().sorted(collator).toList();
    }

    public static void main(String[] args) {
        Locale arabic = Locale.forLanguageTag("ar-SY");
        Locale german = Locale.GERMANY;
        System.out.println(checkoutMessage(Locale.US, 3, 1234.5));
        System.out.println(checkoutMessage(german, 3, 1234.5));
        System.out.println(formattedNow(arabic, ZoneId.of("Asia/Damascus")));
        System.out.println(sortedNames(
            Arrays.asList("Zoe", "Adam", "Mona", "Omar"), Locale.US));
    }
}
