package com.util.sequence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-10 18:16
 */
public class SequenceCallbackImpl implements SequenceCallback{
    private Map<String, Sequence> cache = new ConcurrentHashMap<>();


    @Override
    public void init() {
        // 查询所有未使用的
        // select * from tsp_sequence_register
        // 将其修改为使用中入库 ；放入缓存


    }

    @Override
    public Long nextVal(String type,String name) {
        return null;
    }

    private static class Sequence{
        private String sequenceId;
        private int step;
        private Long max;
        private Long current;

        public Sequence(String sequenceId) {
            this.sequenceId = sequenceId;
        }

        public synchronized Long next() {
            if (max >= current + step) {
                current += step;
                return current;
            }else {
                SequenceInfo info = getSequenceInfo(sequenceId);
                max = info.getMax();
                step = info.getStep();
                current = info.getCurrent();
                return current;
            }
        }

        private SequenceInfo getSequenceInfo(String sequenceId) {
            // 1.查询sequence_define
            // 2.无则新增（新增报错主键冲突 就再次查询-for update读）
            // 3.有责更新(通过次数控制器控制更新)当前值
            return new SequenceInfo();
        }
    }
}
