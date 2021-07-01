package peergos.shared.user;

import jsinterop.annotations.JsType;
import peergos.shared.cbor.CborObject;
import peergos.shared.cbor.Cborable;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@JsType
public class TagsListItem implements Cborable {

    public final String tagName;

    public TagsListItem(String tagName) {
        this.tagName = tagName;
    }

    public static TagsListItem build(String text) {
        return new TagsListItem(text);
    }

    @Override
    public CborObject toCbor() {
        Map<String, Cborable> cbor = new TreeMap<>();
        cbor.put("t", new CborObject.CborString(tagName.substring(0, Math.min(tagName.length(), 60))));
        return CborObject.CborMap.build(cbor);
    }

    public static TagsListItem fromCbor(Cborable cbor) {
        if (! (cbor instanceof CborObject.CborMap))
            throw new IllegalStateException("Incorrect cbor for TagsListItem: " + cbor);
        CborObject.CborMap map = (CborObject.CborMap) cbor;
        String tagName = map.getString("t");
        return new TagsListItem(tagName);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        TagsListItem that = (TagsListItem) o;

        return Objects.equals(tagName, that.tagName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }

    @Override
    public String toString() {
        return "tagName:" + tagName;
    }
}
