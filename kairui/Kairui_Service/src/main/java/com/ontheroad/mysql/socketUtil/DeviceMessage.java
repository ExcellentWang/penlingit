package com.ontheroad.mysql.socketUtil;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceMessage {
    private static final String HEADER = "LDCT";
     private String deviceType;
     private String deviceID;        // 12 digits
     private String commandType;

     private ArrayList<String> args;

    private String messageString;

    // device types
    public static final String GAS_HEATER = "01";               // 燃气热水器
    public static final String ELECTRIC_HEATER = "02";          // 储水式电热水器
    public static final String HEAT_PUMP = "03";                // 空气能热水器
    public static final String HANGING_FURNACE = "04";          // 壁挂炉
    public static final String SOLAR_HEATER = "05";             // 太阳能

    // command types
    public static String HEART_BEAT = "xtpc";             // 心跳包

    public DeviceMessage() {}
    
    public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public ArrayList<String> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<String> args) {
		this.args = args;
	}

	public String getMessageString() {
		return messageString;
	}

	public DeviceMessage(String messageString) {
        this.messageString = messageString;
        try {
            parse();
        } catch (MessageException ex) {
            ex.printStackTrace();
        }
    }

    public DeviceMessage(String deviceType, String deviceID, String commandType) {
        this.deviceType = deviceType;
        this.deviceID = deviceID;
        this.commandType = commandType;
        this.messageString = toString();
    }

    public DeviceMessage(String deviceType, String deviceID, String commandType, ArrayList<String> args) {
        this.deviceType = deviceType;
        this.deviceID = deviceID;
        this.commandType = commandType;
        this.args = new ArrayList<>(args);
        this.messageString = toString();
    }

    public DeviceMessage(String deviceNum, String commandType, ArrayList<String> args) {

        // LDCT01201704230001
        this.deviceType = deviceNum.substring(4, 5);
        this.deviceID = deviceNum.substring(6, 17);
        this.commandType = commandType;
        this.args = new ArrayList<>(args);
        this.messageString = toString();
    }

    public void setMessageString(String msg) {
        this.messageString = msg;
        try {
            parse();
        } catch (MessageException ex) {
            ex.printStackTrace();
        }
    }

    // <LDCT01201704230001:xtds,052,2017,05,02,20,12,38,91>
    @Override
    public String toString() {
        return "<" + body() + checksum() + ">";
    }

    private String body() {
        return HEADER + deviceType + deviceID + ":" + commandType + "," + length() + argString() + ",";
    }

    private String argString() {
        if(args != null && !args.isEmpty()) {
             return "," + StringUtils.join(args, ",");
        } else {
            return "";
        }
    }

    private String length() {
        Integer len = 28;
        len += commandType.length();
        if(args != null && !args.isEmpty()) {
            for(String arg : args) {
                len += arg.length() + 1;
            }
        }
        return String.format("%03d", len);
    }

    private String checksum() {
        int sum = 0;
        for(char c: body().toCharArray()) {
            sum += (int)c;
        }
        String hex = Integer.toHexString(sum).toUpperCase();
        return hex.substring(hex.length() - 2);
    }


    private void parse() throws MessageException {
        if (StringUtils.isNotEmpty(messageString)) {
            // <LDCT01201704230001:xtds,052,2017,05,02,20,12,38,91>
            // <LDCT01201704230002:scyc,060,0000,00,00,20,00,00,000,0,0,0,0,0,0,0,0,FD>
            // LDCT                      : fixed header
            // 01                        : device type
            // 201704230001              : device id
            // xtds                      : command type
            // 052                       : command length
            // 2017,05,02,20,12,38       : args
            // 91                        : checksum

            Pattern pattern = Pattern.compile("(?<=LDCT)(\\d{2})(\\d{12}):(\\w+),(\\d{3})([,\\-\\+\\w\\.+]*"
            		+ "),(\\w{2})(?=>)");
            Matcher matcher = pattern.matcher(messageString);

            if(!matcher.find()) {
                return;
            }
            deviceType = matcher.group(1);
            deviceID = matcher.group(2);
            commandType = matcher.group(3);

            // if args
            // ",2017,05,02,20,12,38"
            if (StringUtils.isNotEmpty(matcher.group(5))) {
                args = new ArrayList<>(Arrays.asList(matcher.group(5).substring(1).split(",")));
            }

            if(!matcher.group(4).equals(length())) {
//                throw new MessageException("Length Not Match");
            }

            if(!matcher.group(6).equals(checksum())) {
//                throw new MessageException("Checksum Not Match");
            }
        }
    }


    public static void main(String[] args) {
    	//
        String m1_s = "<LDCT01201704230001:wcal,063,-1.0,-1.0,-1.0,-1.0,1,998,3950,7E>";
        DeviceMessage m1 = new DeviceMessage(m1_s);
        System.out.println(m1.toString());
        String m1_s2 = "<<LDCT01201704230001:xtds,052,2017,05,02,20,12,38,91>";
        DeviceMessage m12 = new DeviceMessage(m1_s2);
        System.out.println(m12.toString());
    }
}
