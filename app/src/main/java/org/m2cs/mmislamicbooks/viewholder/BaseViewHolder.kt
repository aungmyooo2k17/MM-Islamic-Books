package org.m2cs.mmislamicbooks.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mDetechedFromWindow: Boolean = false

    init {
        //        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this)

        //        EventBus eventBus = EventBus.getDefault();
        //        if (!eventBus.isRegistered(this)) {
        //            eventBus.register(this);
        //        }

        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                mDetechedFromWindow = false
            }

            override fun onViewDetachedFromWindow(v: View) {
                mDetechedFromWindow = true
            }
        })
    }

    abstract fun bind(data: T)

    //    @Subscribe(threadMode = ThreadMode.MAIN)
    //    public void onEvent(Object obj) {
    //
    //    }

    override fun onClick(v: View) {

    }
}