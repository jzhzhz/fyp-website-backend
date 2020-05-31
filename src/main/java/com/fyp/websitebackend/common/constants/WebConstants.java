package com.fyp.websitebackend.common.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class WebConstants {
    public static final List<String> CARD_PIC_TYPES =
            new ArrayList<>(Arrays.asList("image/png", "image/jpeg"));

    public static final List<String> EXCEL_FILE_TYPES =
            new ArrayList<>(Arrays.asList(
                    "application/vnd.ms-excel",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            ));

    public static final int RECORD_NOT_DEPRECATED = 0;
    public static final int RECORD_IS_DEPRECATED = 1;
}
