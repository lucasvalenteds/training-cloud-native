package io.lucasvalenteds.toll.web.toll;

import java.util.Objects;

public class TollResponseBody {

    private final String message;

    public TollResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TollResponseBody that = (TollResponseBody) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public String toString() {
        return "TollResponseBody{" +
            "message='" + message + '\'' +
            '}';
    }
}
