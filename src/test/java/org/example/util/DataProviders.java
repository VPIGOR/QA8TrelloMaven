package org.example.util;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> loginPositive() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginPositive.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginNegative() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginNegative.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object> names() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/names.data")));

        List<Object> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line);
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"log1", "pass1"});
        data.add(new Object[]{"log2", "pass2"});
        data.add(new Object[]{"log3", "pass3"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 2; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataProviderRandomLogin() {
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            data.add(new Object[]{this.generateDigitsLiters(2, 10), this.generateDigitsLiters(2, 10)});
        }
        return data.iterator();
    }

    private String generateRandomSymbols(int minSymbolsCount, int maxSymbolsCount) {
        Random gen = new Random();
        int countOfSimbols = minSymbolsCount + gen.nextInt(maxSymbolsCount - minSymbolsCount);
        String pass = "";
        for (int i = 0; i < countOfSimbols; i++) {
            pass += (char) (48 + gen.nextInt(76));
        }
        return pass;
    }
    private String generateDigitsLiters(int minSymbolsCount, int maxSymbolsCount) {
        String symbol = "0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        char[] arr = symbol.toCharArray();
        Random gen = new Random();
        int countOfSimbols = minSymbolsCount + gen.nextInt(maxSymbolsCount - minSymbolsCount);
        String pass = "";
        for (int i = 0; i < countOfSimbols; i++) {
            int num = gen.nextInt(62);
            pass += arr[num];
        }
        return pass;
    }


    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }


}


