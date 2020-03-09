package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class VisionUtilTest {

    @Test
    public void testGenerateTags() throws UtilException {
        VisionUtil visionUtil = new VisionUtil();
        List<String> tags = visionUtil.generateTags("https://storage.cloud.google.com/worldvision-7.appspot.com/clouds-monochrome-black-smoke-wallpaper.jpg?cloudshell=false&supportedpurview=project");
        for (String tag : tags) {
            System.out.println(tag);
        }
    }
}