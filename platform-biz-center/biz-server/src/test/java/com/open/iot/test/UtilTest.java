package com.open.iot.test;

import com.open.iot.modelandutils.utils.IndexUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author miaosc
 * @date 12/2/2019
 */
@Slf4j
public class UtilTest {

    @Test
    public void testNextSeq(){
        log.info("自增序列:{}",IndexUtil.nextSequence());
    }
}
