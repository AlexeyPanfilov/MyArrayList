import org.example.PanfArrList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PanfArrListTest<T> {

    private PanfArrList<Object> list = new PanfArrList<>();

    private final int SIZE_TO_FILL = 10;
    private final String ITEM = "Item";

    private void fillList() {
        for (int i = 0; i < SIZE_TO_FILL; i++) {
            list.add(ITEM + i);
        }
    }

    @Test
    @DisplayName(value = "list with illegal capacity initialization exception test")
    void setListWithCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PanfArrList<>(-1));
    }

    @Test
    @DisplayName(value = "Add and size test")
    void add() {
        fillList();
        int indexToGet = 9;
        Assertions.assertEquals(SIZE_TO_FILL, list.size());
        Assertions.assertEquals(ITEM + indexToGet, list.get(indexToGet));
        list.remove(3);
        Assertions.assertEquals(SIZE_TO_FILL - 1, list.size());
        list.add("s");
        list.add("d");
        Assertions.assertEquals(SIZE_TO_FILL + 1, list.size());
    }

    @Test
    @DisplayName(value = "list grow test")
    void grow() {
        PanfArrList<Object> growList = new PanfArrList<>(1);
        Assertions.assertEquals(0, growList.size());
        growList.add(1);
        Assertions.assertEquals(1, growList.size());
        Assertions.assertFalse(growList.size() > 1);
        growList.add(2);
        Assertions.assertEquals(2, growList.size());
    }

    @Test
    @DisplayName(value = "isEmpty test")
    void isEmpty() {
        Assertions.assertTrue(list.isEmpty());
        list.add("sdfdfdsf");
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName(value = "contains test")
    void contains() {
        list.add("Obj");
        Assertions.assertTrue(list.contains("Obj"));
        Assertions.assertFalse(list.contains("sff"));
    }

    @Test
    @DisplayName(value = "remove by equals test")
    void removeByEquals() {
        list.add("One");
        Assertions.assertTrue(list.remove("One"));
        list.add("Two");
        list.add("Three");
        list.add("Four");
        Assertions.assertTrue(list.contains("Four"));
        Assertions.assertTrue(list.remove("Four"));
        Assertions.assertFalse(list.contains("Four"));
        Assertions.assertFalse(list.remove("something"));
    }

    @Test
    @DisplayName(value = "clearing test")
    void clear() {
        fillList();
        Assertions.assertEquals(SIZE_TO_FILL, list.size());
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    @DisplayName(value = "get by index test")
    void getByIndex() {
        int indexToGet = 1;
        fillList();
        Assertions.assertEquals(ITEM + indexToGet, list.get(indexToGet));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    @DisplayName(value = "element set test")
    void setElement() {
        fillList();
        int indexForSet = 5;
        Assertions.assertEquals(ITEM + indexForSet, list.set(indexForSet, "New Item"));
        Assertions.assertEquals("New Item", list.get(indexForSet));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(), ""));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.set(-1, ""));
    }

    @Test
    @DisplayName(value = "add by index test")
    void addByIndex() {
        fillList();
        String item = "Obj";
        list.add(0, item);
        Assertions.assertEquals(item, list.get(0));
        Assertions.assertEquals(SIZE_TO_FILL + 1, list.size());
        list.add(list.size(), item);
        Assertions.assertEquals(item, list.get(list.size() - 1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, item));
    }

    @Test
    @DisplayName(value = "remove by index")
    void removeByIndex() {
        fillList();
        int index = 0;
        Assertions.assertEquals(ITEM + index, list.remove(index));
        Assertions.assertEquals(SIZE_TO_FILL - 1, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.remove(-1));
        Assertions.assertFalse(list.contains(ITEM + index));
    }

    @Test
    @DisplayName(value = "indexOf test")
    void indexOf() {
        fillList();
        int index = 1;
        Assertions.assertEquals(index, list.indexOf(ITEM + index));
        Assertions.assertEquals(-1, list.indexOf(""));
    }
}
