package com.open.iot.modelandutils.utils;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Date;
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

    /**
     * java的进程id
     */
    public static final short PROCESS_ID;

    static {
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        PROCESS_ID = Short.parseShort(processId);
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
     * @return
     */
    public static String nextSequence() {
        short nextInt = (short) (sequence.incrementAndGet());
        //时间+进程Id+自增序列
        Instant now = Instant.now();
        long milli = now.toEpochMilli();
        byte[] bytes = new byte[12];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putLong(0, milli);
        byteBuffer.putShort(8, PROCESS_ID);
        byteBuffer.putShort(10, nextInt);
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
}
