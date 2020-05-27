package Utils;

public class Pair<T, M> {

    /* Класс "Пара" хранит в себе два шаблонных поля first и second. В данных полях можно хранить любые необходимые
       парные данные. Доступ к полям экземпляра можно получить через методы. */

    private T first;
    private M second;

    public Pair(T value1, M value2)
    {
        first = value1;
        second = value2;
    }

    public T getFirst()
    {
        return first;
    }

    public M getSecond()
    {
        return second;
    }

    public void setFirst(T value)
    {
        first = value;
    }

    public void setSecond(M value)
    {
        second = value;
    }

}