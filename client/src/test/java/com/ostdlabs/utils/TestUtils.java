package com.ostdlabs.utils;

import com.google.gson.Gson;
import com.ostdlabs.model.BankAccount;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

public class TestUtils {

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static BankAccount getAccountMock() {
        return new BankAccount();
    }

    public static List<BankAccount> getListAccountMock() {
        return Arrays.asList(new BankAccount());
    }

    public static String getJsonString(Object object) {
        return new Gson().toJson(object);
    }
}
