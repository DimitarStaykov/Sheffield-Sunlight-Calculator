package com.example.staykov.sunlight;

/**
 * Created by Staykov on 4/3/2017.
 */
import android.content.Context;
import android.content.res.AssetManager;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class QuoteBank {

    private Context mContext;

    public QuoteBank(Context context) {
        this.mContext = context;
    }

    public String[] readLine(String path) {

        List<String> mLines = new ArrayList<>();

        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                mLines.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] mLines2 = new String[mLines.size()];
        mLines2 = mLines.toArray(mLines2);
        return mLines2;
    }
}
