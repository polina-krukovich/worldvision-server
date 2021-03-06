package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.cloud.vision.v1.ColorInfo;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class VisionUtilTest {

    @Test
    public void testGenerateTags() throws UtilException {
        VisionUtil visionUtil = new VisionUtil();
        Set<String> tags = visionUtil.generateTags("gs://worldvision-7.appspot.com/11-03-2020-22-21-53-852-01-balance-of-nature.adapt.jpg");
        assertNotEquals(tags.size(), 0);
    }

    @Test
    public void testDetectColors() throws UtilException {
        VisionUtil visionUtil = new VisionUtil();
        List<ColorInfo> colorInfos = visionUtil.detectColors("gs://worldvision-7.appspot.com/11-03-2020-22-21-53-852-01-balance-of-nature.adapt.jpg");
        assertNotEquals(colorInfos.size(), 0);
    }
}