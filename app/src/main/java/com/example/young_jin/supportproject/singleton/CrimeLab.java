package com.example.young_jin.supportproject.singleton;

import android.content.Context;

import com.example.young_jin.supportproject.models.Crime;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Young-Jin on 2016-02-08.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    public CrimeLab(Context mAppContext) {
        this.mAppContext = mAppContext;
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 10; i++) {
            Crime c = new Crime();
            c.setmTItle("범죄 #" + i);
            c.setmSolved(i%2==0);
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getmCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime c : mCrimes){
            if(c.getmID().equals(id))
                return c;
        }
        return null;
    }

    public void deleteCrime(Crime c){
        mCrimes.remove(c);
    }
}
