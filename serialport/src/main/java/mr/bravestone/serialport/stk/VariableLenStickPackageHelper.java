package mr.bravestone.serialport.stk;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class VariableLenStickPackageHelper implements AbsStickPackageHelper {
    private int offset = 0;
    private int lenIndex = 0;
    private int lenSize = 2;
    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    private List<Byte> mBytes;
    private int lenStartIndex;
    private int lenEndIndex;

    public VariableLenStickPackageHelper(ByteOrder byteOrder, int lenSize, int lenIndex, int offset) {
        this.byteOrder = byteOrder;
        this.lenSize = lenSize;
        this.offset = offset;
        this.lenIndex = lenIndex;
        mBytes = new ArrayList<>();
        lenStartIndex = lenIndex;
        lenEndIndex = lenIndex + lenSize - 1;
        if (lenStartIndex > lenEndIndex) {
            throw new IllegalStateException("lenStartIndex>lenEndIndex");
        }
    }

    private int getLen(byte[] src, ByteOrder order) {
        int re = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            for (byte b : src) {
                re = (re << 8) | (b & 0xff);
            }
        } else {
            for (int i = src.length - 1; i >= 0; i--) {
                re = (re << 8) | (src[i] & 0xff);
            }
        }
        return re;
    }

    @Override
    public byte[] execute(InputStream is) {
        mBytes.clear();
        int count = 0;
        int len = -1;
        byte temp;
        byte[] result;
        int msgLen = -1;
        byte[] lenField = new byte[lenSize];
        try {
            while ((len = is.read()) != -1) {
                temp = (byte) len;
                if (count >= lenStartIndex && count <= lenEndIndex) {
                    lenField[count - lenStartIndex] = temp;
                    if (count == lenEndIndex) {
                        msgLen = getLen(lenField, byteOrder);
                    }
                }
                count++;
                mBytes.add(temp);
                if (msgLen != -1) {
                    if (count == msgLen + offset) {
                        break;
                    } else if (count > msgLen + offset) {//error
                        len = -1;
                        break;
                    }
                }
            }
            if (len == -1) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        result = new byte[mBytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = mBytes.get(i);
        }
        return result;
    }
}
