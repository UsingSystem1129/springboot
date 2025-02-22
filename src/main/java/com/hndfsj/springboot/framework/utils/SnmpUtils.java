package com.hndfsj.springboot.framework.utils;

import org.snmp4j.*;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * TODO：
 * </pre>
 *
 * @author MS
 * @date 2019/7/26
 */
public class SnmpUtils {

    private String ip;
    private String port;

    //获取cpu使用率
    public static void collectCPU() {
        TransportMapping transport = null;
        Snmp snmp = null;
        CommunityTarget target;
        String[] oids = {"1.3.6.1.2.1.25.3.3.1.2"};
//        String[] oids={"1.3.6.1.4.1.311"};
//        String[] oids={"1.3.6.1.2.1.25.2"};
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++) {
                columns[i] = new OID(oids[i]);
            }
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if (list.size() == 1 && list.get(0).getColumns() == null) {
                System.out.println(" null");
            } else {
                int percentage = 0;
                for (TableEvent event : list) {
                    VariableBinding[] values = event.getColumns();
                    if (values != null) {
                        percentage += Integer.parseInt(values[0].getVariable().toString());
                    }
                }
                System.out.println("CPU利用率为：" + percentage / list.size() + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
                if (snmp != null) {
                    snmp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取内存相关信息
    public static void collectMemory() {
        TransportMapping transport = null;
        Snmp snmp = null;
        CommunityTarget target;
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
                "1.3.6.1.2.1.25.2.3.1.3",  //descr
                "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
                "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
                "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        String PHYSICAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.2";//物理存储
        String VIRTUAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.3"; //虚拟存储
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++) {
                columns[i] = new OID(oids[i]);
            }
            @SuppressWarnings("unchecked") List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if (list.size() == 1 && list.get(0).getColumns() == null) {
                System.out.println(" null");
            } else {
                for (TableEvent event : list) {
                    VariableBinding[] values = event.getColumns();
                    if (values == null) {
                        continue;
                    }
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    String oid = values[0].getVariable().toString();
                    if (PHYSICAL_MEMORY_OID.equals(oid)) {
                        System.out.println("PHYSICAL_MEMORY----->物理内存大小：" + (long) totalSize * unit / (1024 * 1024 * 1024) + "G   内存使用率为：" + (long) usedSize * 100 / totalSize + "%");
                    } else if (VIRTUAL_MEMORY_OID.equals(oid)) {
                        System.out.println("VIRTUAL_MEMORY----->虚拟内存大小：" + (long) totalSize * unit / (1024 * 1024 * 1024) + "G   内存使用率为：" + (long) usedSize * 100 / totalSize + "%");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
                if (snmp != null) {
                    snmp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取磁盘相关信息
    public static void collectDisk() {
        TransportMapping transport = null;
        Snmp snmp = null;
        CommunityTarget target;
        String DISK_OID = "1.3.6.1.2.1.25.2.1.4";
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
                "1.3.6.1.2.1.25.2.3.1.3",  //descr
                "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
                "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
                "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++) {
                columns[i] = new OID(oids[i]);
            }
            @SuppressWarnings("unchecked") List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if (list.size() == 1 && list.get(0).getColumns() == null) {
                System.out.println(" null");
            } else {
                for (TableEvent event : list) {
                    VariableBinding[] values = event.getColumns();
                    if (values == null || !DISK_OID.equals(values[0].getVariable().toString())) {
                        continue;
                    }
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    System.out.println(getChinese(values[1].getVariable().toString()) + "   磁盘大小：" + (long) totalSize * unit / (1024 * 1024 * 1024) + "G   磁盘使用率为：" + (long) usedSize * 100 / totalSize + "%");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
                if (snmp != null) {
                    snmp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器进程集合信息
    public static void collectProcess() {
        TransportMapping transport = null;
        Snmp snmp = null;
        CommunityTarget target;
        String[] oids = {"1.3.6.1.2.1.25.4.2.1.1",  //index
                "1.3.6.1.2.1.25.4.2.1.2",  //name
                "1.3.6.1.2.1.25.4.2.1.4",  //run path
                "1.3.6.1.2.1.25.4.2.1.6",  //type
                "1.3.6.1.2.1.25.5.1.1.1",  //cpu
                "1.3.6.1.2.1.25.5.1.1.2"}; //memory
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:127.0.0.1/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++) {
                columns[i] = new OID(oids[i]);
            }
            @SuppressWarnings("unchecked") List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if (list.size() == 1 && list.get(0).getColumns() == null) {
                System.out.println(" null");
            } else {
                for (TableEvent event : list) {
                    VariableBinding[] values = event.getColumns();
                    if (values == null) {
                        continue;
                    }
                    String name = values[1].getVariable().toString();//name
                    String cpu = values[4].getVariable().toString();//cpu
                    String memory = values[5].getVariable().toString();//memory
                    String path = values[2].getVariable().toString();//path
                    System.out.println("name--->" + name + "  cpu--->" + cpu + "  memory--->" + memory + "  path--->" + path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
                if (snmp != null) {
                    snmp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取磁盘的中文名字
     * 解决snmp4j中文乱码问题
     */
    public static String getChinese(String octetString) {
        if (octetString == null || "".equals(octetString) || "null".equalsIgnoreCase(octetString)) {
            return "";
        }
        try {
            String[] temps = octetString.split(":");
            if (temps.length < 4) {
                return octetString;
            }
            byte[] bs = new byte[temps.length];
            for (int i = 0; i < temps.length; i++) {
                bs[i] = (byte) Integer.parseInt(temps[i], 16);
            }
            return new String(bs, "GB2312");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将16进制的时间转换成标准的时间格式
     */
    private static String hexToDateTime(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return "";
        }
        String dateTime = "";
        try {
            byte[] values = OctetString.fromHexString(hexString).getValue();
            int year, month, day, hour, minute;

            year = values[0] * 256 + 256 + values[1];
            month = values[2];
            day = values[3];
            hour = values[4];
            minute = values[5];

            char format_str[] = new char[22];
            int index = 3;
            int temp = year;
            for (; index >= 0; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[4] = '-';
            index = 6;
            temp = month;
            for (; index >= 5; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[7] = '-';
            index = 9;
            temp = day;
            for (; index >= 8; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[10] = ' ';
            index = 12;
            temp = hour;
            for (; index >= 11; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            format_str[13] = ':';
            index = 15;
            temp = minute;
            for (; index >= 14; index--) {
                format_str[index] = (char) (48 + (temp - temp / 10 * 10));
                temp /= 10;
            }
            dateTime = new String(format_str, 0, format_str.length).substring(0, 16);
        } catch (Exception e) {
        }
        return dateTime;
    }


    public static void main(String[] args) {
        SnmpUtils.collectCPU();
        SnmpUtils.collectMemory();
        SnmpUtils.collectDisk();
        SnmpUtils.collectProcess();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
