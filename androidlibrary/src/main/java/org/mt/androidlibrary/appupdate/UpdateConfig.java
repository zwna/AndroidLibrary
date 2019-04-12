package org.mt.androidlibrary.appupdate;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class UpdateConfig implements Parcelable {


    private String mUrl;
    /**
     * 保存路径
     */
    private String mPath;
    /**
     * 保存文件名
     */
    private String mFilename;

    /**
     * 是否显示通知栏
     */
    private boolean isShowNotification = true;
    /**
     * 下载完成后是否自动弹出安装
     */
    private boolean isInstallApk = true;
    /**
     * 通知栏图标：默认取app图标
     */
    private int mNotificationIcon;

    /**
     * 通知栏ID
     */
    private int mNotificationId = Constants.DEFAULT_NOTIFICATION_ID;

    /**
     * 通知栏渠道ID
     */
    private String mChannelId;
    /**
     * 通知栏渠道名称
     */
    private String mChannelName;
    /**
     *  默认{@link Context#getPackageName() + ".fileProvider"}
     */
    private String mAuthority;
    /**
     * 下载失败是否支持点击通知栏重复下载
     */
    private boolean isReDownload = true;
    /**
     * 是否显示百分比
     */
    private boolean isShowPercentage = true;

    /**
     * 是否震动提示，为true时使用通知默认震动
     */
    private boolean isVibrate;

    /**
     * 是否铃声提示,为true时使用通知默认铃声
     */
    private boolean isSound;


    public UpdateConfig() {

    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public String getFilename() {
        return mFilename;
    }

    public void setFilename(String filename) {
        this.mFilename = filename;
    }

    public boolean isShowNotification() {
        return isShowNotification;
    }

    public void setShowNotification(boolean isShowNotification) {
        this.isShowNotification = isShowNotification;
    }

    public String getChannelId() {
        return mChannelId;
    }

    public void setChannelId(String channelId) {
        this.mChannelId = channelId;
    }

    public String getChannelName() {
        return mChannelName;
    }

    public void setChannelName(String channelName) {
        this.mChannelName = channelName;
    }

    public void setNotificationId(int notificationId){
        this.mNotificationId = notificationId;
    }

    public int getNotificationId(){
        return this.mNotificationId;
    }

    public void setNotificationIcon(@DrawableRes int icon){
        this.mNotificationIcon = icon;
    }

    public int getNotificationIcon(){
        return this.mNotificationIcon;
    }

    public boolean isInstallApk() {
        return isInstallApk;
    }

    public void setInstallApk(boolean isInstallApk) {
        this.isInstallApk = isInstallApk;
    }

    public String getAuthority() {
        return mAuthority;
    }

    public void setAuthority(String authority) {
        this.mAuthority = authority;
    }

    public boolean isShowPercentage() {
        return isShowPercentage;
    }

    public void setShowPercentage(boolean showPercentage) {
        isShowPercentage = showPercentage;
    }

    public boolean isReDownload() {
        return isReDownload;
    }

    public void setReDownload(boolean reDownload) {
        isReDownload = reDownload;
    }

    public boolean isVibrate() {
        return isVibrate;
    }

    public void setVibrate(boolean vibrate) {
        isVibrate = vibrate;
    }

    public boolean isSound() {
        return isSound;
    }

    public void setSound(boolean sound) {
        isSound = sound;
    }

    @Override
    public String toString() {
        return "UpdateConfig{" +
                "mUrl='" + mUrl + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mFilename='" + mFilename + '\'' +
                ", isShowNotification=" + isShowNotification +
                ", isInstallApk=" + isInstallApk +
                ", mNotificationIcon=" + mNotificationIcon +
                ", mNotificationId=" + mNotificationId +
                ", mChannelId='" + mChannelId + '\'' +
                ", mChannelName='" + mChannelName + '\'' +
                ", mAuthority='" + mAuthority + '\'' +
                ", isReDownload=" + isReDownload +
                ", isShowPercentage=" + isShowPercentage +
                ", isVibrate=" + isVibrate +
                ", isSound=" + isSound +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUrl);
        dest.writeString(this.mPath);
        dest.writeString(this.mFilename);
        dest.writeByte(this.isShowNotification ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isInstallApk ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mNotificationIcon);
        dest.writeInt(this.mNotificationId);
        dest.writeString(this.mChannelId);
        dest.writeString(this.mChannelName);
        dest.writeString(this.mAuthority);
        dest.writeByte(this.isReDownload ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isShowPercentage ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isVibrate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSound ? (byte) 1 : (byte) 0);
    }

    protected UpdateConfig(Parcel in) {
        this.mUrl = in.readString();
        this.mPath = in.readString();
        this.mFilename = in.readString();
        this.isShowNotification = in.readByte() != 0;
        this.isInstallApk = in.readByte() != 0;
        this.mNotificationIcon = in.readInt();
        this.mNotificationId = in.readInt();
        this.mChannelId = in.readString();
        this.mChannelName = in.readString();
        this.mAuthority = in.readString();
        this.isReDownload = in.readByte() != 0;
        this.isShowPercentage = in.readByte() != 0;
        this.isVibrate = in.readByte() != 0;
        this.isSound = in.readByte() != 0;
    }

    public static final Creator<UpdateConfig> CREATOR = new Creator<UpdateConfig>() {
        @Override
        public UpdateConfig createFromParcel(Parcel source) {
            return new UpdateConfig(source);
        }

        @Override
        public UpdateConfig[] newArray(int size) {
            return new UpdateConfig[size];
        }
    };
}
