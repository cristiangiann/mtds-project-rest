package projectMTDS.utils;

import projectMTDS.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageSnapshot {
    private String name;
    private String id;
    private String url;
    private String previewUrl;

    public ImageSnapshot(Image image){
        this(image.getImageId(), image.getName(), "/api/images/" + image.getImageId());
    }

    public ImageSnapshot(String id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
        this.previewUrl = url + "/preview";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static List<ImageSnapshot> getSnapshotsFromImageList(List<Image> imageList){
        List<ImageSnapshot> snapshots = new ArrayList<>();
        for (Image image: imageList) {
            snapshots.add(new ImageSnapshot(image));
        }
        return snapshots;
    }
}
