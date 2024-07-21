package me.bradcanard.toolrental;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Rental {
    private final Tool tool;
    private final int rentalDays;
    private final int discountPercent;
    private final LocalDate checkoutDate;

    public Rental(Tool tool, LocalDate checkoutDate, int rentalDays, int discountPercent ) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    public float getTotalCharge() {
        float totalCharge = getPreDiscountCharge();
        totalCharge -= getDiscountAmount();
        totalCharge = getCurrencyAmount(totalCharge);
        return totalCharge;
    }

    private int getChargeableDays() {
        // Invalid value for rental days or discount percent. Don't bother to calculate the chargeable days.
        if (rentalDays < 1 || discountPercent < 0 || discountPercent > 100) {
            return 0;
        }
        int chargeableDays = 0;
        for (int i = 1; i <= rentalDays; i++) {
            LocalDate date = checkoutDate.plusDays(i);
            if (!isHoliday(date) && DayOfWeek.SATURDAY.getValue() == date.getDayOfWeek().getValue() && tool.isWeekendCharge()) {
                chargeableDays++;
            }
            else if (!isHoliday(date) && DayOfWeek.SUNDAY.getValue() == date.getDayOfWeek().getValue() && tool.isWeekendCharge()) {
                chargeableDays++;
            }
            else if (tool.isHolidayCharge() && isHoliday(date)) {
                chargeableDays++;
            }
            else if (!isHoliday(date) && date.getDayOfWeek().getValue() < 6) {
                chargeableDays++;
            }
        }
        return chargeableDays;
    }

    private float getPreDiscountCharge() {
        return getChargeableDays() * tool.getDailyCharge();
    }

    private boolean isHoliday(LocalDate date) {
        // Labor Day
        if (date.getMonthValue() == 9 && date.getDayOfMonth() <= 7 && DayOfWeek.MONDAY == date.getDayOfWeek()) {
            return true;
        }
        // Independence Day
        if (date.getMonthValue() == 7 && date.getDayOfMonth() >= 3 && date.getDayOfMonth() <= 5) {
            if (date.getDayOfWeek().getValue() < 6 && date.getDayOfMonth() == 4){
                return true;
            }
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3) {
                return true;
            }
            if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() == 5) {
                return true;
            }
        }
        return false;
    }

    private float getDiscountAmount() {
        return getPreDiscountCharge() * (discountPercent / 100.0f);
    }

    private float getCurrencyAmount(float amount) {
        return Math.round(amount * 100) / 100.0f;
    }

    public String checkout() {
        if (rentalDays < 1) {
            return "The rental days must be greater than 0.";
        }
        if (discountPercent < 0 || discountPercent > 100) {
            return "The discount percent must be between 0 and 100.";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return tool.toString() + System.lineSeparator() +
            "Rental days: " + rentalDays + System.lineSeparator() +
            "Check out date: " + checkoutDate.format(dateFormatter) + System.lineSeparator() +
            "Due date: " + checkoutDate.plusDays(rentalDays).format(dateFormatter) + System.lineSeparator() +
            "Daily rental charge: " + dollarFormat.format(tool.getDailyCharge()) + System.lineSeparator() +
            "Charge days: " + getChargeableDays() + System.lineSeparator() +
            "Pre-discount charge: " + dollarFormat.format(getPreDiscountCharge()) + System.lineSeparator() +
            "Discount percent: " + discountPercent + "%" + System.lineSeparator() +
            "Discount amount: " + dollarFormat.format(getCurrencyAmount(getDiscountAmount())) + System.lineSeparator() +
            "Final charge: " + dollarFormat.format(getTotalCharge());
    }
}
