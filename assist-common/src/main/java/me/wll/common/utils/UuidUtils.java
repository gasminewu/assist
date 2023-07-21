package me.wll.common.utils;

import java.util.UUID;


public class UuidUtils {

    public static String uuid() {
    	UUID uuid = UUID.randomUUID();
    	return Long.toHexString(uuid.getMostSignificantBits()) + Long.toHexString(uuid.getLeastSignificantBits());
    }

	
}
