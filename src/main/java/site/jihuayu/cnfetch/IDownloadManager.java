package site.jihuayu.cnfetch;

public interface IDownloadManager {

    void cancel();

    void background();

    boolean isDone();

    DownloadStatus getStatus();

    float getCompletePercentage();

    String getTaskTitle();

    public void start(String threadName);

}
