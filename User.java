/**
 * Created by Arunan on 2/18/2017.
 */

 /*
    A model for the User which we are going to create and process
 */
public class User {
    private String name;
    private String userId;

    private int thumbLength;
    private int indexFingerLength;
    private int middleFingerLength;
    private int ringFingerLength;
    private int pinkieFingerLength;

    private int thumbWidth;
    private int indexFingerWidth;
    private int middleFingerWidth;
    private int ringFingerWidth;
    private int pinkieFingerWidth;

    public User (
            String name,
            String id,
            int thumbLength,
            int indexFingerLength,
            int middleFingerLength,
            int ringFingerLength,
            int pinkieFingerLength,
            int thumbWidth,
            int indexFingerWidth,
            int middleFingerWidth,
            int ringFingerWidth,
            int pinkieFingerWidth
    ){
        this.name = name;
        this.userId = id;
        this.thumbLength = thumbLength;
        this.indexFingerLength = indexFingerLength;
        this.middleFingerLength = middleFingerLength;
        this.ringFingerLength = ringFingerLength;
        this.pinkieFingerLength = pinkieFingerLength;
        this.thumbWidth = thumbWidth;
        this.indexFingerWidth = indexFingerWidth;
        this.middleFingerWidth = middleFingerWidth;
        this.ringFingerWidth = ringFingerWidth;
        this.pinkieFingerWidth = pinkieFingerWidth;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public int getThumbLength() {
        return thumbLength;
    }

    public int getIndexFingerLength() {
        return indexFingerLength;
    }

    public int getMiddleFingerLength() {
        return middleFingerLength;
    }

    public int getRingFingerLength() {
        return ringFingerLength;
    }

    public int getPinkieFingerLength() {
        return pinkieFingerLength;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public int getIndexFingerWidth() {
        return indexFingerWidth;
    }

    public int getMiddleFingerWidth() {
        return middleFingerWidth;
    }

    public int getRingFingerWidth() {
        return ringFingerWidth;
    }

    public int getPinkieFingerWidth() {
        return pinkieFingerWidth;
    }
}
