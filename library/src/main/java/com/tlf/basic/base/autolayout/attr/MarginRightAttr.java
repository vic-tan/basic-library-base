package com.tlf.basic.base.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tanlifei on 15/12/5.
 */
public class MarginRightAttr extends AutoAttr
{
    public MarginRightAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.MARGIN_RIGHT;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        if(!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
        {
            return ;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.rightMargin = val;
    }
}
