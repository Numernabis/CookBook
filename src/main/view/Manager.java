package main.view;

public class Manager {
    public final DataViewer dataViewer;
    public final NoteViewer noteViewer;
    public final SupportViewer supportViewer;

    public Manager(){
        dataViewer = new DataViewer();
        noteViewer = new NoteViewer();
        supportViewer = new SupportViewer();

    }
}
