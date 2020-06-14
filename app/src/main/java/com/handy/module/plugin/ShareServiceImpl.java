package com.handy.module.plugin;

import com.handy.module.annotation.Module;

/**
 * @anchor: handy
 * @date: 2020-06-13
 * @description:
 */
@Module()
public class ShareServiceImpl implements ShareService {

    @Override
    public String getName() {
        return "ShareModule";
    }

    @Override
    public int getNumber() {
        return 64;
    }

    @Override
    public Integer getCount(int index, Integer ok) {
        return 128;
    }
}
