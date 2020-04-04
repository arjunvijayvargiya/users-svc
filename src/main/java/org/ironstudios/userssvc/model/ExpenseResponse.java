package org.ironstudios.userssvc.model;

public class ExpenseResponse<T> {

    int status;
    T reason;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResponse() {
        return reason;
    }

    public void setResponse(T response) {
        this.reason = response;
    }

    public ExpenseResponse(int status, T response) {
        this.status = status;
        this.reason = response;
    }
}
