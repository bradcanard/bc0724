package me.bradcanard.toolrental;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<String, Tool> tools;

    public Inventory () {
        tools = new HashMap<>();
        tools.put("CHNS", new Tool("CHNS", "Stihl", "Chainsaw", 1.49f, false, true));
        tools.put("LADW", new Tool("LADW", "Werner", "Ladder", 1.99f, true, false));
        tools.put("JAKD", new Tool("JAKD", "DeWalt", "Jackhammer", 2.99f, false, false));
        tools.put("JAKR", new Tool("JAKR", "Ridgid", "Jackhammer", 2.99f, false, false));
    }

    public Tool getTool(String code) {
        return tools.get(code);
    }
}
