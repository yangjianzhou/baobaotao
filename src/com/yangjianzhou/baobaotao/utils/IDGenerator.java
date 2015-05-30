package com.yangjianzhou.baobaotao.utils;

import java.util.UUID;

/**
 * Created by yangjianzhou on 15-5-30.
 */
public class IDGenerator {

    public static String generatorID() {
        UUID uuid = UUID.randomUUID();
        StringBuilder idBuilder = new StringBuilder();
        String idStr = new String(uuid.toString());
        idBuilder.append(idStr.substring(0, 8));
        idBuilder.append(idStr.substring(9, 13));
        idBuilder.append(idStr.substring(14, 18));
        idBuilder.append(idStr.substring(19, 23));
        idBuilder.append(idStr.substring(24));
        return idBuilder.toString();
    }

}
