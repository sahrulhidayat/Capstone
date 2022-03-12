package com.sahrulhidayat.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.sahrulhidayat.core.domain.model.GameModel

class DiffCallback(private val mOldList: List<GameModel>, private val mNewList: List<GameModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].id == mNewList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldList[oldItemPosition]
        val newEmployee = mNewList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.background == newEmployee.background
    }
}