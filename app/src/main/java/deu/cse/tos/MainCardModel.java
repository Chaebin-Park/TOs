package deu.cse.tos;


public class MainCardModel {

    String text;
    String[] array;
    int viewType;

    public MainCardModel(String text, int viewType) {
        this.text = text;
        this.viewType = viewType;
    }

    public MainCardModel(String[] textarrays,int viewType) {
        this.array = textarrays;
        this.viewType = viewType;
    }

    public String getText() {
        return text;
    }

    public String[] getArray() {
        return array;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
