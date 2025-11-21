package model;

public class VideoModule extends Module {
    private String videoUrl;

    public VideoModule(String id, String name, int duration, String videoUrl) {
        super(id, name, "Video", duration);
        this.videoUrl = videoUrl;
    }

    @Override
    public int getDuration() { return duration; }

    @Override
    public boolean isCompleted() { return true; } // Giả định hoàn thành khi xem

    public String getVideoUrl() { return videoUrl; }

    @Override
    public String toString() {
        return super.toString() + ", videoUrl='" + videoUrl + '\'' + '}';
    }
}
