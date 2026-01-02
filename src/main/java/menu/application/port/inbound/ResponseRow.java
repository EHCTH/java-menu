package menu.application.port.inbound;

import java.util.List;

public class ResponseRow {
    private final List<String> categories;
    private final List<ResponseCoach> responseCoaches;

    public ResponseRow(List<String> categories, List<ResponseCoach> responseCoaches) {
        this.categories = categories;
        this.responseCoaches = responseCoaches;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<ResponseCoach> getResponseCoaches() {
        return responseCoaches;
    }
}
