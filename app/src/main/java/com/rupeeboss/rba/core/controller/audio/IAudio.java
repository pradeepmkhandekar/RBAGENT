package com.rupeeboss.rba.core.controller.audio;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.model.AudioEntity;

/**
 * Created by IN-RB on 02-02-2017.
 */
public interface IAudio  {
    void uploadAudioRecord(AudioEntity audioEntity, IResponseSubcriber iResponseSubcriber);
}
