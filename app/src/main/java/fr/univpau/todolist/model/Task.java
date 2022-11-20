package fr.univpau.todolist.model;

public class Task {

    private boolean mChecked;
    private String mTitle;
    private String mDate;
    private String mTime;

    public Task(boolean checked, String title, String date, String time) {
        this.setChecked(checked);
        this.setTitle(title);
        this.setDate(date);
        this.setTime(time);

        System.out.println("Tache crée: " + getTitle());
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
