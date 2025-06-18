package retoPragma.MicroTrazabilidad.domain.model;

import java.util.List;

public class CollectionModel<T> {
    private List<T> items;

    public CollectionModel(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

}
