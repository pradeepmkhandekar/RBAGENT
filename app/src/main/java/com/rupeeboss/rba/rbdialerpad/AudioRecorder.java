package com.rupeeboss.rba.rbdialerpad;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import com.rupeeboss.rba.utility.Utility;

import java.io.File;

/**
 * Created by Rajeev Ranjan on 21/10/2016.
 */

public class AudioRecorder {
    String TAG = "AudioRecorder";
    // static AudioRecorder audioRecorder;
    static MediaRecorder mediaRecorder;
    File audiofile;

//    public static AudioRecorder getObject() {
//        if (audioRecorder == null) {
//            audioRecorder = new AudioRecorder();
//
//        }
//        return audioRecorder;
//    }

    public void startRecording(String mobile, int audioSource, Context context) {

       // String fileName = Utility.EmpCode + Utility.getCurrentMobileDateTime() + "_" + mobile;
        String fileName =  Utility.getCurrentMobileDateTime() + "_" + mobile;
        Log.d("FILENAME", fileName);
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
        }
        //File f = new File(Environment.getExternalStorageDirectory(), "CallerAgent");
        File f = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/CallerAgent/Audios");
        if (!f.exists()) {
            f.mkdirs();
        }

        try {
            audiofile = new File(f.getAbsolutePath() + "/" + fileName + ".3gp");
            //audiofile= file.createNewFile();

            // audiofile = File.createTempFile(fileName, ".3gp", f);
        } catch (Exception e) {
            Log.e(TAG, "external storage access error");
            return;
        }

        try {
            mediaRecorder.setAudioSource(audioSource);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(String.valueOf(audiofile));
            Log.d("FilePath", String.valueOf(audiofile));
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {

        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
    }

    public String getFilePath() {
        return audiofile.getAbsolutePath();
    }
}
