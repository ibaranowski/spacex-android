package com.spacex.rockets.presentation.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

abstract class BaseRecyclerViewAdapter<Model, ViewModel : BaseItemViewModel<Model>>(
    items: Collection<Model> = emptyList()
) : RecyclerView.Adapter<BaseItemViewHolder<Model, ViewModel, *>>() {

    protected val items = items.toMutableList()

    open val itemClickSubject : Subject<ItemModel<Model>> = PublishSubject.create()
    protected open var observeClicks = true

    private var attachListener: View.OnAttachStateChangeListener? = null

    override fun onBindViewHolder(holder: BaseItemViewHolder<Model, ViewModel, *>, position: Int) {
        val item = items[position]
        holder.bindTo(item, position)
    }

    override fun getItemCount() = items.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        attachListener = object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(p0: View?) {
                (0 until itemCount)
                    .mapNotNull { recyclerView.findViewHolderForAdapterPosition(it) }
                    .filterIsInstance<BaseItemViewHolder<*, *, *>>()
                    .forEach { it.attach() }
            }

            override fun onViewDetachedFromWindow(p0: View?) {
                (0 until itemCount)
                    .mapNotNull { recyclerView.findViewHolderForAdapterPosition(it) }
                    .filterIsInstance<BaseItemViewHolder<*, *, *>>()
                    .forEach { it.detach() }
            }
        }

        recyclerView.addOnAttachStateChangeListener(attachListener)
    }

    override fun onViewAttachedToWindow(holder: BaseItemViewHolder<Model, ViewModel, *>) {
        super.onViewAttachedToWindow(holder)
        holder.attach()
        if (observeClicks) {
            holder.itemView.setOnClickListener {
                val pos = holder.adapterPosition
                if (pos in 0 until items.size) {
                    itemClickSubject.onNext(ItemModel(pos, items[pos]))
                    holder.viewModel.onItemClick()
                }
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseItemViewHolder<Model, ViewModel, *>) {
        super.onViewDetachedFromWindow(holder)
        if (observeClicks) {
            holder.itemView.setOnClickListener(null)
        }
        holder.detach()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnAttachStateChangeListener(attachListener)
    }

    open fun removeItem(position: Int): Model {
        val item = items.removeAt(position)
        notifyItemRemoved(position)
        return item
    }

    open fun moveItem(fromPosition: Int, toPosition: Int): Model {
        val model = items[fromPosition]
        val prev = items.removeAt(fromPosition)
        items.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, prev)
        notifyItemMoved(fromPosition, toPosition)
        return model
    }

    open fun setItems(newItems: List<Model>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun insertItems(newItems: List<Model>) {
        items.addAll(newItems)
        notifyItemRangeInserted(0, newItems.size)
    }

    fun clearItems() {
        val count = items.count()
        items.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun addItem(newItem: Model) {
        items.add(newItem)
        notifyItemInserted(itemCount - 1)
    }

    fun observeClickedItem(): Observable<ItemModel<Model>> = itemClickSubject
}