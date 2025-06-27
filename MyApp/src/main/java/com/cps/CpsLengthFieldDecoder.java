package com.cps;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-06-27 12:43
 */
public class CpsLengthFieldDecoder extends LengthFieldBasedFrameDecoder {
    public CpsLengthFieldDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected long getUnadjustedFrameLength(ByteBuf buf, int offset, int length, ByteOrder order) {
        long frameLength = 0;
        byte[] lenghtByte = new byte[length];

        buf.getBytes(offset, lenghtByte);

        String len = new String(lenghtByte);
        if (len != "") {
            frameLength = Long.parseLong(len);
        }
        return frameLength;

    }
}
