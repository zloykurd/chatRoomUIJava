package com.techapp.james.myapplication.util;


import java.text.SimpleDateFormat;
import java.util.*;

public class DefaultTimeFormatter implements TimeFormatter {

    @Override
    public String getFormattedTimeText(Calendar createdAt) {
        return new SimpleDateFormat("HH:mm").format(createdAt.getTime());
    }
}
