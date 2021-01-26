
package network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerStyle implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BeerStyle() {
    }

    /**
     * 
     * @param data
     * @param message
     * @param status
     */
    public BeerStyle(String message, List<Datum> data, String status) {
        super();
        this.message = message;
        this.data = data;
        this.status = status;
    }

    protected BeerStyle(Parcel in) {
        message = in.readString();
        data = in.createTypedArrayList(Datum.CREATOR);
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeTypedList(data);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeerStyle> CREATOR = new Creator<BeerStyle>() {
        @Override
        public BeerStyle createFromParcel(Parcel in) {
            return new BeerStyle(in);
        }

        @Override
        public BeerStyle[] newArray(int size) {
            return new BeerStyle[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
