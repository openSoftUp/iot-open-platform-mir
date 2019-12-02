package com.open.iot.modelandutils.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 索引生成工具
 *
 * @author huy
 * @date 2018年12月21日
 */
public class IndexUtil {

    private static Logger LOGGER= LoggerFactory.getLogger(IndexUtil.class);

    /**
     * java的进程id
     */
    public static final short PROCESS_ID;

    /**
     * 机器号
     */
    public static final int MACHINE_NO;

    static {
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        if (processName.contains("@")) {
            PROCESS_ID = (short) Integer.parseInt(processName.substring(0, processName.indexOf('@')));
        } else {
            PROCESS_ID = (short) processName.hashCode();
        }
        MACHINE_NO = generateMachineNo();
    }

    /**
     * 自增序列
     */
    private static AtomicInteger sequence = new AtomicInteger(Instant.now().getNano());

    /**
     * UUID生成器
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }


    /**
     * 生成自增序列,分布式也可以用
     *
     * <pre>
     *     生成规则:8字节时间戳+4个字节的机器码+2个字节的进程id+2个字节的自增序列
     * </pre>
     *
     * @return
     */
    public static String nextSequence() {
        short nextInt = (short) (sequence.incrementAndGet());
        //时间+进程Id+自增序列
        Instant now = Instant.now();
        long milli = now.toEpochMilli();
        byte[] bytes = new byte[16];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putLong(0, milli);
        byteBuffer.putInt(8, MACHINE_NO);
        byteBuffer.putShort(12, PROCESS_ID);
        byteBuffer.putShort(14, nextInt);
        return HexUtil.toHexString(bytes);
    }

    /**
     * 生成16个长度的字符串
     *
     * @return
     */
    public static String generate16String() {
        Random random = new Random();
        byte[] bytes = new byte[8];
        //生成随机数字
        random.nextBytes(bytes);
        return HexUtil.toHexString(bytes);
    }

    /**
     * 获取随机long
     *
     * @return
     */
    public static long getRandomIdx() {
        Random random = new Random();
        return random.nextLong();
    }

    /**
     * 用给定参数生成id
     *
     * @param domainId
     * @param timeS
     * @param bandNo
     * @return
     */
    public static String generateIndex(long domainId, long timeS, String bandNo) {
        String dateString = DateUtil.format(new Date(timeS), "yyyyMMddHHmmss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%05d", domainId));
        stringBuilder.append(dateString);
        stringBuilder.append(bandNo);
        return stringBuilder.toString();
    }

    /**
     * 用给定参数生成字符串
     *
     * @param domainId
     * @param timeS
     * @param sysIdx
     * @return
     */
    public static String generateAlarmIndex(long domainId, long timeS, long sysIdx) {
        String dateString = DateUtil.format(new Date(timeS), "yyyyMMddHHmmss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%05d", domainId));
        stringBuilder.append(dateString);
        stringBuilder.append(String.format("%08d", sysIdx));
        return stringBuilder.toString();
    }

    /**
     * 获取机器号
     *
     * @return
     */
    private static int generateMachineNo() {
        int machinePiece;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    ByteBuffer bb = ByteBuffer.wrap(mac);
                    try {
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                        sb.append(bb.getChar());
                    } catch (BufferUnderflowException shortHardwareAddressException) {
                        // mac with less than 6 bytes. continue
                    }
                }
            }
            machinePiece = sb.toString().hashCode();
        } catch (Throwable t) {
            // exception sometimes happens with IBM JVM, use SecureRandom instead
            machinePiece = (new SecureRandom().nextInt());
            LOGGER.warn("Failed to get machine identifier from network interface, using SecureRandom instead");
        }
        machinePiece = machinePiece & 0x00ffffff;
        return machinePiece;
    }
}
