package me.bradcanard.toolrental;

public class Tool {
    private final String code;
    private final String brand;
    private final String type;
    private final float dailyCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public Tool(String code, String brand, String type, float dailyCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.brand = brand;
        this.type = type;
        this.dailyCharge = dailyCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getCode () {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public String getType () {
        return type;
    }

    public float getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public String toString() {
        return "Tool code: " + code + System.lineSeparator() +
            "Tool type: " + type + System.lineSeparator() +
            "Tool brand: " + brand;
    }
}
