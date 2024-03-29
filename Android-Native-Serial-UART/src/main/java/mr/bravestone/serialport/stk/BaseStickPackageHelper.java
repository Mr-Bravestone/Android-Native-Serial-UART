package mr.bravestone.serialport.stk;

import android.os.SystemClock;

import java.io.IOException;
import java.io.InputStream;

public class BaseStickPackageHelper implements AbsStickPackageHelper {
    public BaseStickPackageHelper() {
    }

    @Override
    public byte[] execute(InputStream is) {
        try {
            int available = is.available();
            if (available > 0) {
                byte[] buffer = new byte[available];
                int size = is.read(buffer);
                if (size > 0) {
                    return buffer;
                }
            } else {
                SystemClock.sleep(50);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
