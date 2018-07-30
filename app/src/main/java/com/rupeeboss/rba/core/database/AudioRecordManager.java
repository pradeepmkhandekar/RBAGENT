package com.rupeeboss.rba.core.database;

import android.util.Log;

import com.rupeeboss.rba.core.model.AudioRecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class AudioRecordManager {

    static AudioRecordManager audioRecordManager;

    public static AudioRecordManager getInstance() {

        if (audioRecordManager == null) {
            audioRecordManager = new AudioRecordManager();
        }
        return audioRecordManager;
    }

    public long addAudioRecord(AudioRecordEntity entity) {
        AudioRecordEntity recordEntity = new AudioRecordEntity(entity);
        long aa = recordEntity.save();
        return recordEntity.save();
    }

    public long updateAudioRecord(long id, int isSync) {

        AudioRecordEntity recordEntity = AudioRecordEntity.findById(AudioRecordEntity.class, id);
        recordEntity.setSync(isSync);
        return recordEntity.save();
    }

    public List<AudioRecordEntity> getAudioRecordEntity() {
        List<AudioRecordEntity> list = new ArrayList<AudioRecordEntity>();

        try {
            list = AudioRecordEntity.find(AudioRecordEntity.class, "IS_SYNC = ?", new String[]{"0"});
            Log.d("RecorderEntity", "" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteRecord(int id) {
        AudioRecordEntity recordEntity = AudioRecordEntity.findById(AudioRecordEntity.class, id);
        return recordEntity.delete();
    }
}

