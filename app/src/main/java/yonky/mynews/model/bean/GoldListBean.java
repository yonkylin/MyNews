package yonky.mynews.model.bean;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GoldListBean {
    private String objectId;
    private String createdAt;
    private String title;
    private int collectionCount;
    private int commentsCount;
    private String url;
    private GoldListUserBean user;
    private GoldListScreenshotBean screenshot;
    public static class GoldListUserBean {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
    public static class GoldListScreenshotBean{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GoldListUserBean getUser() {
        return user;
    }

    public void setUser(GoldListUserBean user) {
        this.user = user;
    }

    public GoldListScreenshotBean getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(GoldListScreenshotBean screenshot) {
        this.screenshot = screenshot;
    }
}
