package deu.cse.tos;

public class OralSupplies {
    private int remainingDate;
    private String itemName;
    private String recommendedDate;

    public OralSupplies(int remainingDate, String itemName, String recommendedDate) {
        this.remainingDate = remainingDate;
        this.itemName = itemName;
        this.recommendedDate = recommendedDate;
    }

    public int getRemainingDate() {
        return this.remainingDate;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getRecommendedDate() {
        return this.recommendedDate;
    }

    public void setRemainingDate(int remainingDate) {
        this.remainingDate = remainingDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setRecommendedDate(String recommendedDate) {
        this.recommendedDate = recommendedDate;
    }
}
