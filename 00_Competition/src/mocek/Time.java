package mocek;

public class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time(int time) {
        hours = time / 3600;
        minutes = (time % 3600) / 60;
        seconds = time % 60;
    }

    public Time(String time) {
        String[] parts = time.split(":");
        hours = Integer.parseInt(parts[0]);
        hours = Integer.parseInt(parts[1]);
        hours = Integer.parseInt(parts[2]);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getSecondsTotal() {
        return hours * 3600 + minutes * 60 + seconds;
    }
    
    public String timeToString() {             
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public Time difference(Time other) {
        return new Time(other.getSecondsTotal() - this.getSecondsTotal());
    }

}
