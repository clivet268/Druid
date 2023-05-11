package com.Clivet268.Druid.Block;

import java.util.HashMap;
import java.util.Map;

public interface IDruidPlant {
    Map<Attribute, Double> attributes = new HashMap<>();

    default IDruidPlant addAttribute(Attribute attribute, double intin) {
        attributes.put(attribute, intin);
        return this;
    }

    default double getValue(Attribute sin) {
        if (this.attributes.containsKey(sin)) {
            return attributes.get(sin);
        }
        return 0;
    }

}
