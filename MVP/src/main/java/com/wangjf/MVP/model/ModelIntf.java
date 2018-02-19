package com.wangjf.MVP.model;

import java.util.Map;

/**
 * Created by wangjf on 17-11-21.
 */

public interface ModelIntf {
    void LoadBean(final OnModelListener listener, Map<String, String> params);
}
