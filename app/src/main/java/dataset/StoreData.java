package dataset;

/**
 * Created by Baek on 2015-04-27.
 */

public class StoreData {
    public String getText_contents() {
        return text_contents;
    }

    public void setText_contents(String text_contents) {
        this.text_contents = text_contents;
    }

    private String text_contents;

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    private int images;

    public StoreData(String text_contents) {
        this.text_contents = text_contents;
        //this.images = images;
    }

}
