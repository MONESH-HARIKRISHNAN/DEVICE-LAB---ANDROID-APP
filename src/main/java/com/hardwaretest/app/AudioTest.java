package com.hardwaretest.app;

import android.content.Context;
import android.media.AudioManager;

import java.util.ArrayList;
import java.util.List;

// AudioTest.java
public class AudioTest {
    private final AudioManager audioManager;

    public AudioTest(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        // Test audio capabilities
        results.add(new TestResult("Audio Service", "Available", true));
        
        // Volume levels
        int musicVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxMusicVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        results.add(new TestResult("Music Volume", musicVolume + "/" + maxMusicVolume, true));

        int ringVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        int maxRingVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        results.add(new TestResult("Ring Volume", ringVolume + "/" + maxRingVolume, true));

        // Audio mode
        int audioMode = audioManager.getMode();
        String modeText = "";
        switch (audioMode) {
            case AudioManager.MODE_NORMAL: modeText = "Normal"; break;
            case AudioManager.MODE_RINGTONE: modeText = "Ringtone"; break;
            case AudioManager.MODE_IN_CALL: modeText = "In Call"; break;
            case AudioManager.MODE_IN_COMMUNICATION: modeText = "In Communication"; break;
            case AudioManager.MODE_CALL_REDIRECT:
            case AudioManager.MODE_CALL_SCREENING:
            case AudioManager.MODE_COMMUNICATION_REDIRECT:
                break;
        }
        results.add(new TestResult("Audio Mode", modeText, true));

        // Speaker phone
        boolean speakerOn = audioManager.isSpeakerphoneOn();
        results.add(new TestResult("Speakerphone", speakerOn ? "On" : "Off", true));

        return results;
    }

    public void cleanup() {

    }
}

