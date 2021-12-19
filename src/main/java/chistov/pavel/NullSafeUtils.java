package chistov.pavel;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class NullSafeUtils {

  private NullSafeUtils() {
    throw new UnsupportedOperationException();
  }

  public static <V> V getValueSafety(Supplier<V> getValueSupplier) {
    try {
      return getValueSupplier.get();
    } catch (Exception ex) {
      return null;
    }
  }

  public static <V> V getValueSafetyOrDefault(Supplier<V> getValueSupplier, V defaultValue) {
    V v = getValueSafety(getValueSupplier);
    return Objects.nonNull(v) ? v : defaultValue;
  }

  public static <V> boolean isValuePresent(Supplier<V> getValueSupplier) {
    return Objects.nonNull(getValueSafety(getValueSupplier));
  }

  public static <A, B> B getDeepValueSafety(A a, Function<A, B> firstGetterFunction) {
    if (Objects.isNull(a) || Objects.isNull(firstGetterFunction)) {
      return null;
    }
    return firstGetterFunction.apply(a);
  }

  public static <A, B> boolean isDeepValuePresent(A a, Function<A, B> firstGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);
    return Objects.nonNull(b);
  }

  public static <A, B> B getDeepValueSafetyOrDefault(A a,
                                                     B defaultValue,
                                                     Function<A, B> firstGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);
    return Objects.nonNull(b) ? b : defaultValue;
  }

  public static <A, B, C> C getDeepValueSafety(A a,
                                               Function<A, B> firstGetterFunction,
                                               Function<B, C> secondGetterFunction) {
    B b = getDeepValueSafety(a, firstGetterFunction);

    if (Objects.isNull(b) || Objects.isNull(secondGetterFunction)) {
      return null;
    }

    return secondGetterFunction.apply(b);
  }

  public static <A, B, C> boolean isDeepValuePresent(A a,
                                                     Function<A, B> firstGetterFunction,
                                                     Function<B, C> secondGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);
    return Objects.nonNull(c);
  }

  public static <A, B, C> C getDeepValueSafetyOrDefault(A a,
                                                        C defaultValue,
                                                        Function<A, B> firstGetterFunction,
                                                        Function<B, C> secondGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);
    return Objects.nonNull(c) ? c : defaultValue;
  }

  public static <A, B, C, D> D getDeepValueSafety(A a,
                                                  Function<A, B> firstGetterFunction,
                                                  Function<B, C> secondGetterFunction,
                                                  Function<C, D> thirdGetterFunction) {
    C c = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction);

    if (Objects.isNull(c) || Objects.isNull(thirdGetterFunction)) {
      return null;
    }

    return thirdGetterFunction.apply(c);
  }

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

  public static <A, B, C, D, E> E getDeepValueSafety(A a,
                                                     Function<A, B> firstGetterFunction,
                                                     Function<B, C> secondGetterFunction,
                                                     Function<C, D> thirdGetterFunction,
                                                     Function<D, E> fourthGetterFunction) {
    D d = getDeepValueSafety(a,
                             firstGetterFunction,
                             secondGetterFunction,
                             thirdGetterFunction);

    if (Objects.isNull(d) || Objects.isNull(fourthGetterFunction)) {
      return null;
    }

    return fourthGetterFunction.apply(d);
  }

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

    if (Objects.isNull(e) || Objects.isNull(fifthGetterFunction)) {
      return null;
    }

    return fifthGetterFunction.apply(e);
  }

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

    if (Objects.isNull(f) || Objects.isNull(sixthGetterFunction)) {
      return null;
    }

    return sixthGetterFunction.apply(f);
  }

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

    if (Objects.isNull(g) || Objects.isNull(seventhGetterFunction)) {
      return null;
    }

    return seventhGetterFunction.apply(g);
  }

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

    if (Objects.isNull(h) || Objects.isNull(eighthGetterFunction)) {
      return null;
    }

    return eighthGetterFunction.apply(h);
  }

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

    if (Objects.isNull(i) || Objects.isNull(ninthGetterFunction)) {
      return null;
    }

    return ninthGetterFunction.apply(i);
  }

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

  @SafeVarargs
  public static <T, V> V getDeepValueSafety(T t,
                                            Function<T, Object> firstGetterFunction,
                                            Function<Object, Object>... getterFunctions) {

    if (Objects.isNull(t) || Objects.isNull(firstGetterFunction)) {
      return null;
    }

    Object object = firstGetterFunction.apply(t);

    for (Function<Object, Object> getterFunction : getterFunctions) {
      if (Objects.isNull(getterFunction)) {
        return null;
      }

      object = getterFunction.apply(object);

      if (Objects.isNull(object)) {
        return null;
      }
    }
    return (V) object;
  }

  @SafeVarargs
  public static <T, V> boolean isDeepValuePresent(T t,
                                                  Function<T, Object> firstGetterFunction,
                                                  Function<Object, Object>... getterFunctions) {
    V v = getDeepValueSafety(t, firstGetterFunction, getterFunctions);
    return Objects.nonNull(v);
  }

  @SafeVarargs
  public static <T, V> V getDeepValueSafetyOrDefault(T t, V defaultValue,
                                                     Function<T, Object> firstGetterFunction,
                                                     Function<Object, Object>... getterFunctions) {
    V v = getDeepValueSafety(t, firstGetterFunction, getterFunctions);
    return Objects.nonNull(v) ? v : defaultValue;
  }
}
