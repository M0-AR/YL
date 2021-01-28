package Exercise_10._01;

public class Time {
    private long hour;
    private long minute;
    private long second;

    Time () {
       this(System.currentTimeMillis());
    }

    Time (long elapseTime) {
        setTime(elapseTime);
    }


    public Time(long hour, long minute, long second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }


    public long getHour() {
        return hour;
    }

    public long getMinute() {
        return minute;
    }

    public long getSecond() {
        return second;
    }

    public void setTime(long elapseTime) {
        long totalSecond = elapseTime / 1000;
        this.second = totalSecond % 60;
        this.minute = (totalSecond / 60) % 60;
        this.hour = (totalSecond / (60 * 60)) % 24;
    }
}
