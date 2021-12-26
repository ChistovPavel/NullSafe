package chistov.pavel;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Класс с набором вспомагательных методов для поиска и получения значений с определенного уровня вложенности конкретной сущности.
 */
public final class NullSafeUtils {

  private NullSafeUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Метод предназначен для безопасного выполнения метода получения значения. В getValueSupplier можно поместить выражение для получения значения в глубину (по цепочке).
   * Данный метод лучше всего использовать в тех случая, когда вы уверены, что значения на каждом уровне вложенности существует,
   * но для перестраховки от возникновения {@link NullPointerException} хотите воспользоваться доп. механизмом.
   *
   * @param getValueSupplier {@link Supplier} для получения желаемого значения;
   * @param <V>              тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или {@code null}, если желаемого значения не существует.
   */
  public static <V> V getValueSafety(Supplier<V> getValueSupplier) {
    try {
      return getValueSupplier.get();
    } catch (NullPointerException ex) {
      return null;
    }
  }

  /**
   * Метод предназначен для безопасного выполнения метода получения значения или, если значения не существует, значения по умолчанию.
   * В getValueSupplier можно поместить выражение для получения значения в глубину (по цепочке).
   * Данный метод лучше всего использовать в тех случая, когда вы уверены, что значения на каждом уровне вложенности существует,
   * но для перестраховки от возникновения {@link NullPointerException} хотите воспользоваться доп. механизмом.
   *
   * @param getValueSupplier {@link Supplier} для получения желаемого значения;
   * @param defaultValue     значение, которое будет возвращено, если результат выполнения getValueSupplier не существует;
   * @param <V>              тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <V> V getValueSafetyOrDefault(Supplier<V> getValueSupplier, V defaultValue) {
    V v = getValueSafety(getValueSupplier);
    return Objects.nonNull(v) ? v : defaultValue;
  }

  /**
   * Метод предназначен для безопасного выполнения проверки существования желаемого значения.
   * В getValueSupplier можно поместить выражение для получения значения в глубину (по цепочке).
   * Данный метод лучше всего использовать в тех случая, когда вы уверены, что значения на каждом уровне вложенности существует,
   * но для перестраховки от возникновения {@link NullPointerException} хотите воспользоваться доп. механизмом.
   *
   * @param getValueSupplier {@link Supplier} для получения желаемого значения;
   * @param <V>              тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <V> boolean isValuePresent(Supplier<V> getValueSupplier) {
    return Objects.nonNull(getValueSafety(getValueSupplier));
  }

  /**
   * Метод для безопасного получения значения в глубину на один уровень.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                   родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param <A>                 тип прнимаемого значения;
   * @param <B>                 тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B> B getDeepValueSafety(A a, Function<A, B> firstGetterFunction) {
    return Objects.isNull(a) || Objects.isNull(firstGetterFunction) ?
        null :
        firstGetterFunction.apply(a);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на один уровень.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                   родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param <A>                 тип прнимаемого значения
   * @param <B>                 тип возвращаемого значения
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B> boolean isDeepValuePresent(A a, Function<A, B> firstGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);
    return Objects.nonNull(b);
  }

  /**
   * Метод для безопасного получения значения в глубину на один уровень или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                   родительский объект, из которого будет взято желаемое значение;
   * @param defaultValue        значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param <A>                 тип прнимаемого значения;
   * @param <B>                 тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B> B getDeepValueSafetyOrDefault(A a,
                                                     B defaultValue,
                                                     Function<A, B> firstGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);
    return Objects.nonNull(b) ? b : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на два уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C> C getDeepValueSafety(A a,
                                               Function<A, B> firstGetterFunction,
                                               Function<B, C> secondGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);

    return Objects.isNull(b) || Objects.isNull(secondGetterFunction) ?
        null :
        secondGetterFunction.apply(b);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на два уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C> boolean isDeepValuePresent(A a,
                                                     Function<A, B> firstGetterFunction,
                                                     Function<B, C> secondGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);
    return Objects.nonNull(c);
  }

  /**
   * Метод для безопасного получения значения в глубину на два уровня или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue         значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C> C getDeepValueSafetyOrDefault(A a,
                                                        C defaultValue,
                                                        Function<A, B> firstGetterFunction,
                                                        Function<B, C> secondGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);
    return Objects.nonNull(c) ? c : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на три уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D> D getDeepValueSafety(A a,
                                                  Function<A, B> firstGetterFunction,
                                                  Function<B, C> secondGetterFunction,
                                                  Function<C, D> thirdGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);

    return Objects.isNull(c) || Objects.isNull(thirdGetterFunction) ?
        null :
        thirdGetterFunction.apply(c);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на три уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D> boolean isDeepValuePresent(A a,
                                                        Function<A, B> firstGetterFunction,
                                                        Function<B, C> secondGetterFunction,
                                                        Function<C, D> thirdGetterFunction) {
    D d = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction);
    return Objects.nonNull(d);
  }

  /**
   * Метод для безопасного получения значения в глубину на три уровня или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue         значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D> D getDeepValueSafetyOrDefault(A a,
                                                           D defaultValue,
                                                           Function<A, B> firstGetterFunction,
                                                           Function<B, C> secondGetterFunction,
                                                           Function<C, D> thirdGetterFunction) {
    D d = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction);
    return Objects.nonNull(d) ? d : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на четыре уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E> E getDeepValueSafety(A a,
                                                     Function<A, B> firstGetterFunction,
                                                     Function<B, C> secondGetterFunction,
                                                     Function<C, D> thirdGetterFunction,
                                                     Function<D, E> fourthGetterFunction) {
    D d = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction);

    return Objects.isNull(d) || Objects.isNull(fourthGetterFunction) ?
        null :
        fourthGetterFunction.apply(d);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на четыре уровня.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E> boolean isDeepValuePresent(A a,
                                                           Function<A, B> firstGetterFunction,
                                                           Function<B, C> secondGetterFunction,
                                                           Function<C, D> thirdGetterFunction,
                                                           Function<D, E> fourthGetterFunction) {
    E e = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction);
    return Objects.nonNull(e);
  }

  /**
   * Метод для безопасного получения значения в глубину на четыре уровня или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue         значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E> E getDeepValueSafetyOrDefault(A a,
                                                              E defaultValue,
                                                              Function<A, B> firstGetterFunction,
                                                              Function<B, C> secondGetterFunction,
                                                              Function<C, D> thirdGetterFunction,
                                                              Function<D, E> fourthGetterFunction) {
    E e = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction);
    return Objects.nonNull(e) ? e : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на пять уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E, F> F getDeepValueSafety(A a,
                                                        Function<A, B> firstGetterFunction,
                                                        Function<B, C> secondGetterFunction,
                                                        Function<C, D> thirdGetterFunction,
                                                        Function<D, E> fourthGetterFunction,
                                                        Function<E, F> fifthGetterFunction) {
    E e = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction);

    return Objects.isNull(e) || Objects.isNull(fifthGetterFunction) ?
        null :
        fifthGetterFunction.apply(e);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на пять уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E, F> boolean isDeepValuePresent(A a,
                                                              Function<A, B> firstGetterFunction,
                                                              Function<B, C> secondGetterFunction,
                                                              Function<C, D> thirdGetterFunction,
                                                              Function<D, E> fourthGetterFunction,
                                                              Function<E, F> fifthGetterFunction) {
    F f = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction);
    return Objects.nonNull(f);
  }

  /**
   * Метод для безопасного получения значения в глубину на пять уровней или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue         значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E, F> F getDeepValueSafetyOrDefault(A a,
                                                                 F defaultValue,
                                                                 Function<A, B> firstGetterFunction,
                                                                 Function<B, C> secondGetterFunction,
                                                                 Function<C, D> thirdGetterFunction,
                                                                 Function<D, E> fourthGetterFunction,
                                                                 Function<E, F> fifthGetterFunction) {
    F f = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction);
    return Objects.nonNull(f) ? f : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на шесть уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction  {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                  тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E, F, G> G getDeepValueSafety(A a,
                                                           Function<A, B> firstGetterFunction,
                                                           Function<B, C> secondGetterFunction,
                                                           Function<C, D> thirdGetterFunction,
                                                           Function<D, E> fourthGetterFunction,
                                                           Function<E, F> fifthGetterFunction,
                                                           Function<F, G> sixthGetterFunction) {
    F f = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction);

    return Objects.isNull(f) || Objects.isNull(sixthGetterFunction) ?
        null :
        sixthGetterFunction.apply(f);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на шесть уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction  {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                  тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E, F, G> boolean isDeepValuePresent(A a,
                                                                 Function<A, B> firstGetterFunction,
                                                                 Function<B, C> secondGetterFunction,
                                                                 Function<C, D> thirdGetterFunction,
                                                                 Function<D, E> fourthGetterFunction,
                                                                 Function<E, F> fifthGetterFunction,
                                                                 Function<F, G> sixthGetterFunction) {
    G g = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction);
    return Objects.nonNull(g);
  }

  /**
   * Метод для безопасного получения значения в глубину на шесть уровней или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                    родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue         значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction  {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction  {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction  {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction  {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param <A>                  тип прнимаемого значения;
   * @param <B>                  тип промежуточного значения на первом уровне вложенности;
   * @param <C>                  тип промежуточного значения на втором уровне вложенности;
   * @param <D>                  тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                  тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                  тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                  тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E, F, G> G getDeepValueSafetyOrDefault(A a,
                                                                    G defaultValue,
                                                                    Function<A, B> firstGetterFunction,
                                                                    Function<B, C> secondGetterFunction,
                                                                    Function<C, D> thirdGetterFunction,
                                                                    Function<D, E> fourthGetterFunction,
                                                                    Function<E, F> fifthGetterFunction,
                                                                    Function<F, G> sixthGetterFunction) {
    G g = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction);
    return Objects.nonNull(g) ? g : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на семь уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E, F, G, H> H getDeepValueSafety(A a,
                                                              Function<A, B> firstGetterFunction,
                                                              Function<B, C> secondGetterFunction,
                                                              Function<C, D> thirdGetterFunction,
                                                              Function<D, E> fourthGetterFunction,
                                                              Function<E, F> fifthGetterFunction,
                                                              Function<F, G> sixthGetterFunction,
                                                              Function<G, H> seventhGetterFunction) {
    G g = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction);

    return Objects.isNull(g) || Objects.isNull(seventhGetterFunction) ?
        null :
        seventhGetterFunction.apply(g);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на семь уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E, F, G, H> boolean isDeepValuePresent(A a,
                                                                    Function<A, B> firstGetterFunction,
                                                                    Function<B, C> secondGetterFunction,
                                                                    Function<C, D> thirdGetterFunction,
                                                                    Function<D, E> fourthGetterFunction,
                                                                    Function<E, F> fifthGetterFunction,
                                                                    Function<F, G> sixthGetterFunction,
                                                                    Function<G, H> seventhGetterFunction) {
    H h = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction);
    return Objects.nonNull(h);
  }

  /**
   * Метод для безопасного получения значения в глубину на семь уровней или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue          значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E, F, G, H> H getDeepValueSafetyOrDefault(A a,
                                                                       H defaultValue,
                                                                       Function<A, B> firstGetterFunction,
                                                                       Function<B, C> secondGetterFunction,
                                                                       Function<C, D> thirdGetterFunction,
                                                                       Function<D, E> fourthGetterFunction,
                                                                       Function<E, F> fifthGetterFunction,
                                                                       Function<F, G> sixthGetterFunction,
                                                                       Function<G, H> seventhGetterFunction) {
    H h = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction);
    return Objects.nonNull(h) ? h : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на восемь уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E, F, G, H, I> I getDeepValueSafety(A a,
                                                                 Function<A, B> firstGetterFunction,
                                                                 Function<B, C> secondGetterFunction,
                                                                 Function<C, D> thirdGetterFunction,
                                                                 Function<D, E> fourthGetterFunction,
                                                                 Function<E, F> fifthGetterFunction,
                                                                 Function<F, G> sixthGetterFunction,
                                                                 Function<G, H> seventhGetterFunction,
                                                                 Function<H, I> eighthGetterFunction) {
    H h = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction);

    return Objects.isNull(h) || Objects.isNull(eighthGetterFunction) ?
        null :
        eighthGetterFunction.apply(h);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на восемь уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E, F, G, H, I> boolean isDeepValuePresent(A a,
                                                                       Function<A, B> firstGetterFunction,
                                                                       Function<B, C> secondGetterFunction,
                                                                       Function<C, D> thirdGetterFunction,
                                                                       Function<D, E> fourthGetterFunction,
                                                                       Function<E, F> fifthGetterFunction,
                                                                       Function<F, G> sixthGetterFunction,
                                                                       Function<G, H> seventhGetterFunction,
                                                                       Function<H, I> eighthGetterFunction) {
    I i = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction,
                             eighthGetterFunction);
    return Objects.nonNull(i);
  }

  /**
   * Метод для безопасного получения значения в глубину на восемь уровней или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue          значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E, F, G, H, I> I getDeepValueSafetyOrDefault(A a,
                                                                          I defaultValue,
                                                                          Function<A, B> firstGetterFunction,
                                                                          Function<B, C> secondGetterFunction,
                                                                          Function<C, D> thirdGetterFunction,
                                                                          Function<D, E> fourthGetterFunction,
                                                                          Function<E, F> fifthGetterFunction,
                                                                          Function<F, G> sixthGetterFunction,
                                                                          Function<G, H> seventhGetterFunction,
                                                                          Function<H, I> eighthGetterFunction) {
    I i = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction,
                             eighthGetterFunction);
    return Objects.nonNull(i) ? i : defaultValue;
  }

  /**
   * Метод для безопасного получения значения в глубину на девять уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param ninthGetterFunction   {@link Function} для получения желаемого значения на девятом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип промежуточного значения на восьмом уровне вложенности;
   * @param <J>                   тип возвращаемого значения.
   * @return желаемое значение, если все входные параметры не равны {@code null} и оно существует, или
   * {@code null} если хотя бы один из входных параметров равен {@code null} или желаемое значение не существует.
   */
  public static <A, B, C, D, E, F, G, H, I, J> J getDeepValueSafety(A a,
                                                                    Function<A, B> firstGetterFunction,
                                                                    Function<B, C> secondGetterFunction,
                                                                    Function<C, D> thirdGetterFunction,
                                                                    Function<D, E> fourthGetterFunction,
                                                                    Function<E, F> fifthGetterFunction,
                                                                    Function<F, G> sixthGetterFunction,
                                                                    Function<G, H> seventhGetterFunction,
                                                                    Function<H, I> eighthGetterFunction,
                                                                    Function<I, J> ninthGetterFunction) {
    I i = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction,
                             eighthGetterFunction);

    return Objects.isNull(i) || Objects.isNull(ninthGetterFunction) ?
        null :
        ninthGetterFunction.apply(i);
  }

  /**
   * Метод для безопасного выполнения проверки наличия желаемого значения в глубину на девять уровней.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, проверить наличие значения на определенном уровне вложенности.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param ninthGetterFunction   {@link Function} для получения желаемого значения на девятом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип промежуточного значения на восьмом уровне вложенности;
   * @param <J>                   тип возвращаемого значения.
   * @return {@code true}, если значение существует, или {@code false}, если значения не существует.
   */
  public static <A, B, C, D, E, F, G, H, I, J> boolean isDeepValuePresent(A a,
                                                                          Function<A, B> firstGetterFunction,
                                                                          Function<B, C> secondGetterFunction,
                                                                          Function<C, D> thirdGetterFunction,
                                                                          Function<D, E> fourthGetterFunction,
                                                                          Function<E, F> fifthGetterFunction,
                                                                          Function<F, G> sixthGetterFunction,
                                                                          Function<G, H> seventhGetterFunction,
                                                                          Function<H, I> eighthGetterFunction,
                                                                          Function<I, J> ninthGetterFunction) {
    J j = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction,
                             eighthGetterFunction,
                             ninthGetterFunction);
    return Objects.nonNull(j);
  }

  /**
   * Метод для безопасного получения значения в глубину на девять уровней или значения по умолчанию, если желаемого значения не существует.
   * Данный метод лучше всего использовать в тех случая, когда вы не уверены, что значения на каждом уровне вложенности существует.
   * Данный метод позволит безопасно, с точки зрения возникновения {@link NullPointerException}, извлечь значение с определенного уровня вложенности или вернуть
   * значение по умолчанию, если желоемое значение не существует.
   *
   * @param a                     родительский объект, из которого будет изъято желаемое значение;
   * @param defaultValue          значение по умолчанию, которое будет возвращено, если желаемое значение не существует;
   * @param firstGetterFunction   {@link Function} для получения желаемого значения на первом уровне вложенности;
   * @param secondGetterFunction  {@link Function} для получения желаемого значения на втором уровне вложенности;
   * @param thirdGetterFunction   {@link Function} для получения желаемого значения на третьем уровне вложенности;
   * @param fourthGetterFunction  {@link Function} для получения желаемого значения на четвертом уровне вложенности;
   * @param fifthGetterFunction   {@link Function} для получения желаемого значения на пятом уровне вложенности;
   * @param sixthGetterFunction   {@link Function} для получения желаемого значения на шестом уровне вложенности;
   * @param seventhGetterFunction {@link Function} для получения желаемого значения на седьмом уровне вложенности;
   * @param eighthGetterFunction  {@link Function} для получения желаемого значения на восьмом уровне вложенности;
   * @param ninthGetterFunction   {@link Function} для получения желаемого значения на девятом уровне вложенности;
   * @param <A>                   тип прнимаемого значения;
   * @param <B>                   тип промежуточного значения на первом уровне вложенности;
   * @param <C>                   тип промежуточного значения на втором уровне вложенности;
   * @param <D>                   тип промежуточного значения на третьем уровне вложенности;
   * @param <E>                   тип промежуточного значения на четвертом уровне вложенности;
   * @param <F>                   тип промежуточного значения на пятом уровне вложенности;
   * @param <G>                   тип промежуточного значения на шестом уровне вложенности;
   * @param <H>                   тип промежуточного значения на седьмом уровне вложенности;
   * @param <I>                   тип промежуточного значения на восьмом уровне вложенности;
   * @param <J>                   тип возвращаемого значения.
   * @return желаемое значение, если оно существует, или defaultValue, если желаемого значения не существует.
   */
  public static <A, B, C, D, E, F, G, H, I, J> J getDeepValueSafetyOrDefault(A a,
                                                                             J defaultValue,
                                                                             Function<A, B> firstGetterFunction,
                                                                             Function<B, C> secondGetterFunction,
                                                                             Function<C, D> thirdGetterFunction,
                                                                             Function<D, E> fourthGetterFunction,
                                                                             Function<E, F> fifthGetterFunction,
                                                                             Function<F, G> sixthGetterFunction,
                                                                             Function<G, H> seventhGetterFunction,
                                                                             Function<H, I> eighthGetterFunction,
                                                                             Function<I, J> ninthGetterFunction) {
    J j = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction,
                             fourthGetterFunction,
                             fifthGetterFunction,
                             sixthGetterFunction,
                             seventhGetterFunction,
                             eighthGetterFunction,
                             ninthGetterFunction);
    return Objects.nonNull(j) ? j : defaultValue;
  }
}