package com.rupeeboss.rba.core.database;

import android.content.Context;

import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.utility.Utility;

import java.util.List;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class AudioRecorderFacade {
    Context context;

    public AudioRecorderFacade(Context context) {
        this.context = context;
    }

    public long insertAudioRecord(AudioEntity audioEntity) {
        String convertedTo64 = Utility.getBase64String(audioEntity.getFile_name());
        audioEntity.setConvetedBase64(convertedTo64);
        return audioEntity.save();
    }


    public long updateAudioRecord(AudioEntity audioEntity) {
        AudioEntity entity = AudioEntity.findById(AudioEntity.class, audioEntity.getId());
        entity.setUploaded(audioEntity.isUploaded());
        return entity.update();
    }

    public boolean deleteAudioRecord(AudioEntity audioEntity) {
        AudioEntity entity = AudioEntity.findById(AudioEntity.class, audioEntity.getId());
        return entity.delete();
    }

    public AudioEntity getAudioRecord(long id) {
        return AudioEntity.findById(AudioEntity.class, id);
    }

    public List<AudioEntity> getAllAudioRecord() {
        return AudioEntity.find(AudioEntity.class, "IS_UPLOADED = ?", "0");
    }
}
