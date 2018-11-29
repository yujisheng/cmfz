package com.baizhi.cmfz.util;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

public class FileUtil {
    public static int getDuration(File file) {
        int length = 0;
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            length = audioHeader.getTrackLength();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}
