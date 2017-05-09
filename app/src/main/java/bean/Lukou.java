package bean;

/**
 * Created by OKMAN on 2017/5/9.
 */

public class Lukou {
    private String lukouID;
    private String redTime;
    private String greenTime;
    private String yellowTime;

    public Lukou(String yellowTime, String lukouID, String redTime, String greenTime) {
        this.yellowTime = yellowTime;
        this.lukouID = lukouID;
        this.redTime = redTime;
        this.greenTime = greenTime;
    }

    public String getLukouID() {
        return lukouID;
    }

    public void setLukouID(String lukouID) {
        this.lukouID = lukouID;
    }

    public String getRedTime() {
        return redTime;
    }

    public void setRedTime(String redTime) {
        this.redTime = redTime;
    }

    public String getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(String greenTime) {
        this.greenTime = greenTime;
    }

    public String getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(String yellowTime) {
        this.yellowTime = yellowTime;
    }

    @Override
    public String toString() {
        return "Lukou{" +
                "lukouID='" + lukouID + '\'' +
                ", redTime='" + redTime + '\'' +
                ", greenTime='" + greenTime + '\'' +
                ", yellowTime='" + yellowTime + '\'' +
                '}';
    }
}
