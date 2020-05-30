package com.fyp.websitebackend.common.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class WebConstants {
    public static final List<String> CARD_PIC_TYPES =
            new ArrayList<>(Arrays.asList("image/png", "image/jpeg"));

    public static final int RECORD_NOT_DEPRECATED = 0;
    public static final int RECORD_IS_DEPRECATED = 1;
}
