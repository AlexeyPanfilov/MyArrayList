package org.example;

import java.util.*;

/**
 * Реализация динамического массива для хранения и манипуляции данными (объектами).
 * <p>Содержит базовые методы {@code add}, {@code size}, {@code isEmpty}, {@code contains}, {@code remove},
 * {@code clear}, {@code get}, {@code set}, {@code indexOf}, {@code trimToSize}.
 * При инициализации объект данной коллекции представляет собой массив минимальной вместимости. В случае, если при добавлении
 * элементов требуется вместимость больше, она увеличивается автоматически. При удалении элементов, вместимость не уменьшается,
 * однако её можно уменьшить методом {@code trimToSize}.
 * <p><strong>Данная реализация не потокобезопасна</strong>, так что в случае использования её в многопоточной среде,
 * если планируется доступ к ней одновременно нескольких потоков для модификации, разработчику необходимо
 * дополнительно синхронизировать используемые методы.
 *
 * @param <T> тип элемента, используемого в данной коллекции
 * @author Алексей Панфилов
 * @version 0.9
 */

public class PanfArrList<T> {

    /**
     * Размер массива при создании списка, а также минимально возможный его размер после удаления элементов и выполнения {@code trimToSize}
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Данная переменная используется внутри класса для изменения вместимости при превышении размера текущего массива
     */
    private int currentCapacity = DEFAULT_CAPACITY;

    /**
     * Массив, используемый для хранения элементов коллекции. Вместимость данного списка это размер
     * массива {@code listOfObjects}. При необходимости его размер увеличивается автоматически
     */
    private Object[] listOfobjects;

    /**
     * Пустая коллекция для создания пустых коллекций и снижения вероятности появления NullPointerException
     */
    private static final Object[] EMPTY_LIST = {};

    /**
     * Размер списка (количество хранимых в нем элементов). При создании == 0, при добавлении элементов инкрементируется,
     * при удалении - декрементируется. При удалении всех элементов сбрасывается в 0
     */
    private int size = 0;

    /**
     * Создает пустой список с вместимостью по умолчанию == {@code DEFAULT_CAPACITY}
     */
    public PanfArrList() {
        this.listOfobjects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой список с заданной вместимостью. При этом вместимость не может быть меньше {@code DEFAULT_CAPACITY}
     */
    public PanfArrList(int capacity) {
        if (capacity > 0) {
            this.listOfobjects = (T[]) new Object[capacity];
            this.currentCapacity = capacity;
        } else if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + capacity);
        } else {
            this.listOfobjects = EMPTY_LIST;
        }
    }


    /**
     * Добавляет элемент в конец списка. Тип элемента должен соответствовать типу списка, указанному при его создании
     */
    public boolean add(T t) {
        if (size >= currentCapacity) {
            this.grow();
        }
        listOfobjects[size] = t;
        size++;
        return true;
    }

    /**
     * Метод для внутреннего использования библиотекой. Увеличивает вместимость списка на 10 при достижении текущего предела
     */
    private Object[] grow() {
        return listOfobjects = Arrays.copyOf(listOfobjects, currentCapacity = this.size + 10);
    }

    /**
     * Возвращает размер списка, т.е количество хранящихся в нем элементов в текущий момент
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает состояние списка: пустой или нет (т.е. не содержит ни одного элемента)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод, позволяющий проверить, содержится ли в списке указанный в параметрах элемент (т.е. встречается в
     * нем хотя бы 1 раз). Возвращает true при первом же совпадении.
     */
    public boolean contains(Object o) {
        for (Object obj : listOfobjects) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет объект из списка по equals. Если объект не найден - операция не выполняется, возвращает false.
     * Работает в комбинации с методом {@code indexOf}, находя индекс элемента и вызывая метод {@code remove(index)}
     */
    public boolean remove(Object o) {
        if (indexOf(o) >= 0) {
            remove(indexOf(o));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Удаляет все элементы из списка. При этом вместимость списка не уменьшается.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            listOfobjects[i] = null;
        }
        size = 0;
    }

    /**
     * Полностью удаляет список и возвращает пустую коллекцию
     */
    public void clearTotal() {
        listOfobjects = EMPTY_LIST;
    }

    /**
     * Возвращает элемент из списка, находя его в массиве по индексу. Если передано некорректное значение индекса,
     * может выкинуть IndexOutOfBoundsException или ArrayIndexOutOfDoundsException
     */
    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        return (T) listOfobjects[index];
    }

    /**
     * Меняет значение элемента, находящееся по переданному индексу. Возвращает значение оригинального элемента (до изменения).
     * Может выкинуть IndexOutOfBoundsException или ArrayIndexOutOfDoundsException при некорректном индексе
     */
    public T set(int index, T element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        T previousElement = (T) listOfobjects[index];
        listOfobjects[index] = element;
        return previousElement;
    }

    /**
     * Добавляет элемент в список по индексу, при этом сдвигая остальные элементы вправо. Может выкинуть IndexOutOfBoundsException или ArrayIndexOutOfDoundsException при некорректном индексе
     */
    public void add(int index, T element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        } else if (index == size) {
            this.add(element);
        } else {
            for (int i = this.size; i > index; i--) {
                if (this.size >= this.currentCapacity) {
                    this.grow();
                }
                listOfobjects[i] = listOfobjects[i - 1];
            }
            listOfobjects[index] = element;
            size++;
        }
    }

    /**
     * Удаляет элемент по индексу. При этом остальные элементы сдвигаются влево, если удаляется не последний элемент. Может выкинуть IndexOutOfBoundsException или ArrayIndexOutOfDoundsException при некорректном индексе
     */
    public T remove(int index) {
        T valueToRemove = this.get(index);
        listOfobjects[index] = null;
        if (index < size - 1) {
            for (int i = index; i < this.size - 2; i++) {
                listOfobjects[i] = listOfobjects[i + 1];
            }
            listOfobjects[size - 1] = null;
        }
        size--;
        return valueToRemove;
    }

    /**
     * Возвращает индекс элемента по equals содержимого ячейки. В случае если такой объект в списке отсутствует, возвращает -1
     */
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (listOfobjects[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Обрезает массив дня хранения элементов по размеру списка (количеству содержащихся в нем элементов).
     * При этом минимальный размер внутреннего массива не может быть меньше {@code DEFAULT_CAPACITY}
     */
    public void trimToSize() {
        if (listOfobjects.length > this.size && this.size > 0) {
            listOfobjects = Arrays.copyOf(listOfobjects, this.size);
        } else if (this.size == 0) {
            listOfobjects = EMPTY_LIST;
        }
    }

    /**Метод сортировки коллекции для внешнего вызова. Вызывает в себе внутренний метод сортировки, который при
     * необходимости может быть изменен без изменения сигнатуры вызываемого из вне метода*/
    public void sort() {
        quickSort((T[]) listOfobjects, 0, this.size - 1);
    }

    /**
     * Внутренний метод сортировки коллекции. Использует алгоритм быстрой сортировки. Недоступен для вызова из вне.
     */
    private void quickSort(T[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot = (T) listOfobjects[(leftMarker + rightMarker) / 2];
        do {
            while (((Comparable)listOfobjects[leftMarker]).compareTo(pivot) < 0) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (((Comparable)listOfobjects[rightMarker]).compareTo(pivot) > 0) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    T tmp = (T) listOfobjects[leftMarker];
                    listOfobjects[leftMarker] = listOfobjects[rightMarker];
                    listOfobjects[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : listOfobjects) {
            if (o != null) {
                sb.append(o).append(", ");
            }
        }
        String s = sb.toString().replaceAll(", $", "");
        sb = new StringBuilder(s).append("]");
        return sb.toString();
    }
}