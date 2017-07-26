package com.wally.pocket.modules.balance;

/**
 * Created by MAV1GA on 26/07/2017.
 */

class PendingExpense {

    public PendingExpense(String concept, String total){
        this.amount = total;
        this.concept = concept;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String concept;
    private String amount;

}
