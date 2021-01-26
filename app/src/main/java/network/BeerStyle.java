
package network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerStyle implements Parcelable
{
    @SerializedName("name")
    @Expose
    private List<Datum> name ;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    @SerializedName("pushId")
    @Expose
    private String pushId ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BeerStyle() {
    }

    public BeerStyle(List<Datum> data) {
        super();
    }

    protected BeerStyle(Parcel in) {
        data = in.createTypedArrayList(Datum.CREATOR);

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;

    }

    public void setName(List<Datum> data) {
        this.name = name;

    }

    public List<Datum> getName() {
    return name;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
