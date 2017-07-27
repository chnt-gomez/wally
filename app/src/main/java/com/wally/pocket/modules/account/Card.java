package com.wally.pocket.modules.account;

/**
 * Created by vicente on 27/07/17.
 */

public class Card {

    private String totalDebt;
    private String cutDay;
    private String cardName;

    public Card(String totalDebt, String cutDay, String cardName) {
        this.totalDebt = totalDebt;
        this.cutDay = cutDay;
        this.cardName = cardName;
    }

    public String getTotalDebt() {

        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getCutDay() {
        return cutDay;
    }

    public void setCutDay(String cutDay) {
        this.cutDay = cutDay;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
