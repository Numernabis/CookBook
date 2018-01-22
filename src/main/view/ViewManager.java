package main.view;

public class ViewManager {
    public final DataViewer dataViewer;
    public final NoteViewer noteViewer;
    public final SupportViewer supportViewer;

    public ViewManager(){
        dataViewer = new DataViewer();
        noteViewer = new NoteViewer();
        supportViewer = new SupportViewer();

    }
}
