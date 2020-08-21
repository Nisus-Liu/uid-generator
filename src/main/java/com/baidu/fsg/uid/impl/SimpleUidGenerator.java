package com.baidu.fsg.uid.impl;

import com.baidu.fsg.uid.BitsAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**简化 workid 配置, 固定设置
 * @author dafei
 * @version 0.1
 * @date 2020/7/29 13:57
 */
public class SimpleUidGenerator extends DefaultUidGenerator{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUidGenerator.class);

    public SimpleUidGenerator() {
    }

    public SimpleUidGenerator(long workerId, int timeBits, int workerBits, int seqBits) {
        this.workerId = workerId;
        this.timeBits = timeBits;
        this.workerBits = workerBits;
        this.seqBits = seqBits;
    }

    public SimpleUidGenerator(long workerId) {
        this.workerId = workerId;
    }


    @Override
    public void afterPropertiesSet() {
        // initialize bits allocator
        bitsAllocator = new BitsAllocator(timeBits, workerBits, seqBits);

        // initialize worker id
        // workerId = workerIdAssigner.assignWorkerId(); // 固定配置 workid
        if (workerId<=0) {
            throw new RuntimeException("Illegal Worker id " + workerId);
        }
        if (workerId > bitsAllocator.getMaxWorkerId()) {
            throw new RuntimeException("Worker id " + workerId + " exceeds the max " + bitsAllocator.getMaxWorkerId());
        }

        LOGGER.info("Initialized bits(1, {}, {}, {}) for workerID:{}", timeBits, workerBits, seqBits, workerId);
    }
}
