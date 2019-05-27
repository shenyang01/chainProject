package com.sy.chainproject.interfance;

import java.util.List;

/**
 * @ data  2019/4/11 17:08
 * @ author  zxcg
 * 蓝牙搜索完成
 */

public interface BluetoothResult<T> {
    void success(List<T> devices);
    void fail(List<T> devices);
}

