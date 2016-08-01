package com.zap.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Prateek on 01/08/16.
 */
@JsonObject
public class BaseVO implements Parcelable {

    @JsonField
    public int status;
    @JsonField
    public int error_code;
    @JsonField
    public String error_title;
    @JsonField
    public String msg;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeInt(this.error_code);
        dest.writeString(this.error_title);
        dest.writeString(this.msg);
    }

    public BaseVO() {
    }

    protected BaseVO(Parcel in) {
        this.status = in.readInt();
        this.error_code = in.readInt();
        this.error_title = in.readString();
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<BaseVO> CREATOR = new Parcelable.Creator<BaseVO>() {
        @Override
        public BaseVO createFromParcel(Parcel source) {
            return new BaseVO(source);
        }

        @Override
        public BaseVO[] newArray(int size) {
            return new BaseVO[size];
        }
    };

    @Override
    public String toString() {
        return "BaseVO{" +
                "status=" + status +
                ", error_code=" + error_code +
                ", error_title='" + error_title + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
