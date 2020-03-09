package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisionUtil {
    private static final class SingletonHolder {
        private static final VisionUtil instance = new VisionUtil();
    }

    private Logger logger = LogManager.getLogger(getClass());

    public static VisionUtil getInstance() {
        return SingletonHolder.instance;
    }

    @VisibleForTesting
    VisionUtil() {

    }

    public List<String> generateTags(String gcsPath) throws UtilException {
//        List<String> tags = new ArrayList<>();
//        tags.addAll(detectText(generateRequest(gcsPath, Type.TEXT_DETECTION)));
//        tags.addAll(detectHandWriting(generateRequest(gcsPath, Type.DOCUMENT_TEXT_DETECTION)));
//        tags.addAll(detectLandMarks(generateRequest(gcsPath, Type.LANDMARK_DETECTION)));
//        tags.addAll(detectLabels(generateRequest(gcsPath, Type.LABEL_DETECTION)));
        List<AnnotateImageRequest> request = generateRequest(gcsPath, Type.LABEL_DETECTION);
        List<String> tags = detectLabels(request);
        return tags;
//        tags.addAll(detectLocalizedObjects(generateRequest(gcsPath, Type.OBJECT_LOCALIZATION)));
//        tags.addAll(detectLogos(generateRequest(gcsPath, Type.LOGO_DETECTION)));
//        return tags;
    }

//    public List<Color> detectColors(String gcsPath) {
//
//    }


    private List<String> detectText(List<AnnotateImageRequest> requests) {
        List<String> tags = new ArrayList<>();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse resp : responses) {
                if (resp.hasError()) {
                    logger.error(resp.getError().getMessage());
                    continue;
                }
                for (EntityAnnotation annotation : resp.getTextAnnotationsList()) {
                    tags.add(annotation.getDescription());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }


    private List<String> detectHandWriting(List<AnnotateImageRequest> requests) {
        List<String> tags = new ArrayList<>();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            client.close();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    logger.error(res.getError().getMessage());
                    continue;
                }
                TextAnnotation annotation = res.getFullTextAnnotation();
                for (Page page: annotation.getPagesList()) {
                    for (Block block : page.getBlocksList()) {
                        for (Paragraph para : block.getParagraphsList()) {
                            for (Word word: para.getWordsList()) {
                                String wordText = "";
                                for (Symbol symbol: word.getSymbolsList()) {
                                    wordText = wordText + symbol.getText();
                                }
                                tags.add(wordText);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }

    private List<String> detectLabels(List<AnnotateImageRequest> requests) throws UtilException {
        List<String> tags = new ArrayList<>();
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    logger.error(res.getError().getMessage());
                    continue;
                }
                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    tags.add(annotation.getDescription());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
        return tags;

    }

    private List<String> detectLandMarks(List<AnnotateImageRequest> requests) {
        List<String> tags = new ArrayList<>();
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    logger.error(res.getError().getMessage());
                    continue;
                }
                for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
                    tags.add(annotation.getDescription());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }

    private List<String> detectLogos(List<AnnotateImageRequest> requests) {
        List<String> tags = new ArrayList<>();
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    logger.error(res.getError().getMessage());
                    continue;
                }
                for (EntityAnnotation annotation : res.getLogoAnnotationsList()) {
                    tags.add(annotation.getDescription());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }

    private List<String> detectLocalizedObjects(List<AnnotateImageRequest> requests) {
        List<String> tags = new ArrayList<>();
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            client.close();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    logger.error(res.getError().getMessage());
                    continue;
                }
                for (LocalizedObjectAnnotation annotation : res.getLocalizedObjectAnnotationsList()) {
                    tags.add(annotation.getName());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }

    private List<AnnotateImageRequest> generateRequest(String gcsPath, Type type) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(type).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);
        return requests;
    }


}
