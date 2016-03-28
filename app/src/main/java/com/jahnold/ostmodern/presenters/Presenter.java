package com.jahnold.ostmodern.presenters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by matthewarnold on 05/01/2016.
 *
 * @param <V> view.
 */
public class Presenter<V> {

    private volatile V view;

    @NonNull
    protected V view() {

        if (view == null) {
            throw new IllegalStateException("You've not bound a view...numpty");
        }

        return view;
    }

    @CallSuper
    public void bindView(@NonNull V view) {

        final V previousView = this.view;

        if (previousView != null) {
            throw new IllegalStateException("Previous view is not unbounded! previousView = " + previousView);
        }

        this.view = view;
    }

    @SuppressWarnings("unchecked")
    @CallSuper
    public void unbindView(@NonNull V view) {

        final V previousView = this.view;

        if (previousView == view) {

            // Bit of magic here
            // When the view is detached, reflection is used to determine the view interface
            // Then a "No Operation" null object is created which simply does nothing
            // This way null checks on the view are not required in the presenters
            Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            Class<V> viewClass = (Class<V>) types[0];
            this.view = NoOp.of(viewClass);
        }
        else {
            throw new IllegalStateException("Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
        }
    }




}
