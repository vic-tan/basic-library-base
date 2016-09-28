package com.tlf.basic.base.adapter;

import android.support.v4.util.SparseArrayCompat;

import com.base.adapter.abslistview.AbsViewHolder;
import com.base.adapter.recycler.RvViewHolder;


/**
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T>
{
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An AbsItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered AbsItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("AbsItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType( item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No AbsItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(RvViewHolder holder, T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType( item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No AbsItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    public void convert(AbsViewHolder holder, T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No AbsItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public ItemViewDelegate getItemViewDelegate(int viewType)
    {
        return delegates.get(viewType);
    }

    public ItemViewDelegate getItemViewDelegate(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position))
            {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(int viewType)
    {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
